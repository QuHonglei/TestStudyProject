<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Charts</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
    <script type="text/javascript" src="../easyui/js/echarts.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
     <script type="text/javascript">
     
        $(function(){
            ajaxtest();
        });
        //定义一个函数（方法）对象，格式：function 方法名（形式参数列表）{ 方法体 }
        function ajaxtest() {
            // alert("hahhahaha")
            $.ajax({
                async:false,
                url:'countProvinceStudent',
                type:'POST',
                dataType : 'json',
                success:function (objects1) {
                    initChart(objects1);
                }
            });
        }
        
		//数据格式化函数（方法）
        function formatData(data) {
            var xAxis = [];  //x轴数据
            var serData =[];  //y轴数据
            for(var i = 0 ; i < data.length ; i++){
                xAxis.push(data[i].province);
                console.log(xAxis);
                serData.push(data[i].num);
                console.log(serData);
            }
            return {
                xAxis : xAxis,
                serData : serData,
            };
        }
        //初始化图表函数（方法）
        function initChart(objects1) {
            var dom = document.getElementById("chartmain"); //定义声明dom
            var myChart = echarts.init(dom); //初始化dom
			myChart.showLoading();  //设置延迟加载
            var option = {  //定义选项
                title: {
                    text: '学生按省份分布',
                    subtext:'数据来自Student表',
                },
                /*
                backgroundColor: '#ccc',
                textStyle: {
                    color: '#000',
                },
                */
                tooltip: {}, //提示
                legend: {  //图例
                    data: ['人数'],
                    textStyle: {
                        color: '#000'
                    }
                },
                /* toolbox最好加上，dataView这个在排错的时候挺好用的*/
	       	    toolbox: {
        			show: true,
        			feature: {
            			dataZoom: {
                			yAxisIndex: 'none'
            			},
            			dataView: {readOnly: false},
            			magicType: {type: ['line', 'bar']},
            			restore: {},
            			saveAsImage: {}
        			}
    			},
	            /* X,Y轴中的type属性不能瞎起名，对应的data就是形参*/     	
                xAxis: {
                    type: 'category',
                    data: formatData(objects1).xAxis,
                    axisLabel: {
                        interval: 0,
                        rotate: -30,
                        
                    }

                },

                yAxis: {
                	minInterval: 1, //设置成1保证坐标轴分割刻度显示成整数。
        			axisLabel: {
            			formatter: '{value} 人'
        			},
        			
            		axisLine: {//y轴线的颜色以及宽度
                		show: true,
           			},
                },
                series: [{
                    name: '人数',
                    type: 'bar', //柱状图
                    data: formatData(objects1).serData,
                }]
            };
            myChart.setOption(option, true);
            myChart.hideLoading(); //隐藏延迟加载
        };
    </script>

</head>
<body>
 
<div id="chartmain" style="width: 800px;height:500px;float:left"></div>
</body>

</html>