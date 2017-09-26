<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>美联物业 - 关于平台</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>
<!--关于平台界面-->
	<div class="box" style = "width:auto;">
		<section class = "content" style = "width:auto;">
			<dt style = "text-align:center;">
				<div class = "canva" id="main" style="width: 90%;height:600px;text-align: center;" ></div>
			</dt>
			</dl>
		</section>
	</div>
</body>
</html>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));


    options = {
        title: {
            text: '二手房-套数'
        },

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#ffffff'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['套数','环比']
        },
        xAxis: [
            {
                type: 'category',
                data: ${months},
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '套数',
                min: 30000,
                max: 0,
                interval:3000,
                axisLabel: {
                    formatter: '{value} 套'
                }
            },
            {
                type: 'value',
                name: '环比 [(当月-上月)/上月]*100',
                min: 400,
                max: -100,
                interval: 50,
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],

        series: [
            {
                name:'套数',
                type:'bar',
                data:${numList},
                barWidth:'20',
                itemStyle:{
                    normal:{
                        color:'#b6a2de',
                        barBorderRadius:[6,6,6,6]
                    }
                }
            },

            {
                name:'套数环比',
                type:'line',
                yAxisIndex: 1,
                data:${numRatioList},
				barWidth:'20',
                smooth: true,
				itemStyle:{
                    normal:{
                        color:'#b6a2de',
						barBorderRadius:[1,1,1,1]
                    }
                }
            }
        ]
    };

    myChart.setOption(options);

</script>
