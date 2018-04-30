/**
 * Created by disinuo on 17/3/14.
 */
var  myChart=null;
var currentYear=null;
var mapChart_container=null;
var summaryChart_container=null;
$(function () {
    mapChart_container=$('#mapChart-container');
    summaryChart_container=$('#summaryChart-container');
});


$('#3D-Bubble').click(function () {
    showSummary();
});


function showSummary() {
    $.ajax({
        url:'/data/boss/getSummaryNumOfAllHostels' ,
        success:function (data) {
            console.log(data);
            currentYear=data.year;
            initBubbleChart(data.data);
        },
        error:function (data) {
            console.log(data);
            alert('ERROR');
        }
    });
}

function initBubbleChart(data) {
    summaryChart_container.css("display", "block");
    myChart = echarts.init(document.getElementById('summaryChart-container'));

    var option = {
        title: {
            text: currentYear+'年度各酒店业绩盘点'
        },
        tooltip: {
            trigger: 'item',
            formatter: function (param) {
                var data=param.value;
                return [
                    data[3],
                    ':',
                    data[4],
                    '<br> 总收入：',
                    data[0],
                    '元<br>',
                    '售票总数：',
                    data[1],
                    '<br>活动数：',
                    data[2]
                ].join('');
            }
        },
        legend: {
            right: 10,
            data: [currentYear]
        },
        xAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        yAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
            scale: true
        },
        series: [
            {
                name: currentYear,
                data: data,
                type: 'scatter',
                symbolSize: function (data) {
                    return data[2]*3;
                },
                label: {
                    normal:{
                        show:true,
                        formatter: function (param) {
                            var data=param.value;
                            return  data[3];

                        },
                        position:'bottom'
                    },
                },
                itemStyle: {
                    normal: {
                        shadowBlur: 10,
                        shadowColor: 'rgba(120, 36, 50, 0.5)',
                        shadowOffsetY: 5,
                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                            offset: 0,
                            color: 'rgb(251, 118, 123)'
                        }, {
                            offset: 1,
                            color: 'rgb(204, 46, 72)'
                        }])
                    }
                }
            }
        ]
    };
    myChart.setOption(option);

}
