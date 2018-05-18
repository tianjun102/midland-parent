package com.midland.controller;

import com.midland.configuration.PublicKeyConfiguration;
import com.midland.configuration.SamlProperties;
import com.midland.core.util.ApplicationUtils;
import com.midland.web.Contants.Contant;
import com.midland.web.model.role.Role;
import com.midland.web.model.user.User;
import com.midland.web.service.RoleService;
import com.midland.web.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.Deflater;

@Controller
@RequestMapping("/saml/")
public class SamlLoginController {
    private static Logger logger = LoggerFactory.getLogger(SamlLoginController.class);

    @Autowired
    private PublicKeyConfiguration publicKeyConfiguration;
    @Autowired
    private SamlProperties samlProperties;
    @Autowired
    private UserService userServiceImpl;
    @Resource
    private RoleService roleService;

    @RequestMapping(value = "samlIndex")
    public String indexView(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userInfo");

        if (obj == null || "null".equalsIgnoreCase(obj.toString())) {
            String samlRequest = "";
            // 设置当前访问时间
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            Date now = new Date();
            // 设置samlRequest信息
            String samlRequestString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" ID=\""
                    + RandomStringUtils.random(40, true, false)
                    + "\" Version=\"2.0\" IssueInstant=\""
                    + fmt.format(now)
                    + "\" ProtocolBinding=\"urn:oasis:names.tc:SAML:2.0:bindings:HTTP-Redirect\" ProviderName=\"samlDemo\" AssertionConsumerServiceURL=\"" + samlProperties.getClientResponseUrl() + "\"/>";
            logger.debug("samlIndex", samlRequestString);
            // 将samlRequest信息转码
            samlRequest = encoding(samlRequestString);
            logger.debug("samlIndex", samlRequest);
            // 传递参数至页面，接入应用可将此步骤直接Post认证页面
            model.addAttribute("SAMLRequest", samlRequest);
            model.addAttribute("RelayState", samlProperties.getClientRedirectUrl());
        }
        model.addAttribute("loginName", session.getAttribute("loginName"));

        model.addAttribute("SIAM_LOGIN_URL", samlProperties.getSamlLoginUrl());

        return "saml_sso/saml_sso";
    }

    @RequestMapping(value = "saml_callback")
    public String responseView(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        String loginame = "";
        String SAMLResponse = request.getParameter("SAMLResponse");

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><samlp:Response xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" xmlns=\"urn:oasis:names:tc:SAML:2.0:assertion\" xmlns:xenc=\"http://www.w3.org/2001/04/xmlenc#\" ID=\"oibiegeifmjlgegkkieehnaagjlhahehmhpohind\" IssueInstant=\"2018-05-11T14:31:55Z\" Version=\"2.0\"><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>gBcX4e+uYY6MBz5RkIoXxtpbVTE=</DigestValue></Reference></SignedInfo><SignatureValue>hZJf23jRFxfhnQE0oY/RTdmpqwUJ9Jp9Qn5qmUwA/oM1kMTD3yQC9kt+wGr5tSnNjM0eElaqPaV/PXUekO5vh8I5YOQtHCr/wABZ+FByWEpG3vmjlrDoavFJpBrInckhPo2tPv4UfPtZEDOWgGoctGaOY8l6olxUvGu/8MjhdjQ=</SignatureValue><KeyInfo><KeyValue><RSAKeyValue><Modulus>ttBF3xoaAnwow+LnHFbs7jrMhZxi8N35LymBbdILK1puJPqRluVfJVfDaKzRznKBlr2kKcTU6Hv4YUJO8zjqY5Ve1BDKvv+j6jSkDPi1OHxQa6TclXSs5c5ygcJ+T4UrCbk/AazkWHNbz9QEfcXTf+PqxzZ4VSLk0jvhq0ObqfE=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue></KeyValue></KeyInfo></Signature><samlp:Status><samlp:StatusCode Value=\"urn:oasis:names:tc:SAML:2.0:status:Success\" /></samlp:Status><Assertion ID=\"decafbjbckdmfopblpellbdnbgccdpopfhpmhaii\" IssueInstant=\"2003-04-17T00:46:02Z\" Version=\"2.0\"><Issuer>https://www.opensaml.org/IDP</Issuer><Subject><NameID Format=\"urn:oasis:names:tc:SAML:2.0:nameid-format:emailAddress\">super</NameID><SubjectConfirmation Method=\"urn:oasis:names:tc:SAML:2.0:cm:bearer\"><SubjectConfirmationData InResponseTo=\"FLTUasajncjgYHVJuQEavAlPoByJbVnueiREoMfv\" NotOnOrAfter=\"2019-05-11T14:31:55Z\" Recipient=\"http://localhost:9800/saml-demo/response.do\" /></SubjectConfirmation></Subject><Conditions NotBefore=\"2003-04-17T00:46:02Z\" NotOnOrAfter=\"2019-05-11T14:31:55Z\"><AudienceRestriction><Audience>http://localhost:9800/saml-demo/response.do</Audience></AudienceRestriction></Conditions><AuthnStatement AuthnInstant=\"2018-05-11T14:31:55Z\" sessionindex=\"ST-108-tkQei4O5Ke3X2VDLTN6b-SIAM\"><AuthnContext><AuthnContextClassRef>urn:oasis:names:tc:SAML:2.0:ac:classes:Password</AuthnContextClassRef></AuthnContext></AuthnStatement></Assertion></samlp:Response>");
        SAMLResponse = sb.toString();
        try {
            // 获取认证后信息，首先验证信息有效性，并返回认证信息中的登录账号
            loginame = validate(SAMLResponse);
            User user = userServiceImpl.selectByUsername(loginame);
            if (user == null) {
                    //如果不存在用户,就新增
                    user = new User();
                    user.setUsername(loginame);
                    user.setUserCnName(loginame);
                    user.setUserType(Contant.USER_TYPE_MANAGER);
                    user.setPassword(ApplicationUtils.sha256Hex(Contant.DEFAULT_PASSWORD));
                    userServiceImpl.addUser(user);
            }
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //登录
            subject.login(token);
            final User authUserInfo = userServiceImpl.selectByUsername(user.getUsername());
            //获取用户权限
            List<Role> roles = roleService.selectRolesByUserId(authUserInfo.getId());
            for (Role role : roles) {
                if (role.getRoleType() != null && role.getRoleType() == 0) {
                    //超级管理员
                    authUserInfo.setIsSuper("1");
                }
            }
            authUserInfo.setRoles(roles);
            request.getSession().setAttribute("userInfo", authUserInfo);

        } catch (Exception e) {
           logger.error("saml_callback ",e);
        }

        return "redirect:/";
    }

    public static String encoding(String originalString) {

        String samlRequest = originalString;
        String outputString = null;

        try {
            // 将原始包变成bin byte[]
            byte[] binByte = samlRequest.getBytes("UTF-8");

            // 将bin byte[]压缩
            byte[] output = new byte[1000];
            Deflater compresser = new Deflater(Deflater.BEST_COMPRESSION, true);
            compresser.setInput(binByte); // 要压缩的数据包
            compresser.finish(); // 完成
            compresser.deflate(output); // 压缩，返回的是数据包经过缩缩后的大小

            // 将压缩后的 bin byte[]变为 base64 byte[]
            byte[] base64Input = Base64.encodeBase64(output);
            // 将 base64 byte[]变为base64 String
            String base64String = new String(base64Input, "UTF-8");
            outputString = base64String;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return outputString;

    }

    private String validate(String responseString) throws Exception {
        StringReader sr = new StringReader(responseString);
        String loginName = "";
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(is);

        // Search the Signature element
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }
        Node signatureNode = nl.item(0);

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = fac.unmarshalXMLSignature(new DOMStructure(signatureNode));

        // Create ValidateContext
        DOMValidateContext valCtx = new DOMValidateContext(publicKeyConfiguration.getPubKey(), signatureNode);

        // Validate the XMLSignature
        boolean coreValidity = signature.validate(valCtx);

        // Check core validation status
        if (coreValidity == false) {
            System.err.println("Core validation failed");
            // Check the signature validation status
            boolean sv = signature.getSignatureValue().validate(valCtx);
            System.out.println("Signature validation status: " + sv);
            // check the validation status of each Reference
            List refs = signature.getSignedInfo().getReferences();
            for (int i = 0; i < refs.size(); i++) {
                Reference ref = (Reference) refs.get(i);
                boolean refValid = ref.validate(valCtx);
                System.out.println("Reference[" + i + "] validity status: " + refValid);
            }
        } else {
            System.out.println("Signature passed core validation");
            // 获取登录账号节点信息
            NodeList node = doc.getElementsByTagName("NameID");
            Element e = (Element) node.item(0);
            loginName = e.getTextContent().trim();

            System.out.println("loginName:" + loginName);
        }
        return loginName;
    }
}
