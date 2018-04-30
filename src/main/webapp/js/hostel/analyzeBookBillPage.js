/**
 * Created by disinuo on 17/3/13.
 */
var NAME_LIVE_IN_RATE='上座率';
var NAME_BOOK_NUM='订单量';
var NAME_ROOM_TYPE='活动类型';
var myChart=null;
var data_liveInRate=null;
var data_bookNum=null;
var dateChart_container=null;
var pieChart_container=null;

$(function () {
    dateChart_container=$('#dateChart-container');
    pieChart_container=$('#pieChart-container');
    show();

});


$('#year').click(function (e) {
    showYear();
});

$('#roomType').click(function (e) {
    showRoomType();
});



function initPieChart(data_input,name) {
    pieChart_container.css("display", "block");
    dateChart_container.css("display", "none");

    myChart = echarts.init(document.getElementById('pieChart-container'));
    var data_max=0;
    data_input.forEach(function (item) {
        if(item.value>data_max) data_max=item.value;
    });
    var option = {
        title:{
            text:name,
            left: 'center',
            top: 20,
            textStyle: {
                color: '#000'
            }
        },
        visualMap: {
            min: 0,
            max: data_max,
            left: 'left',
            top: 'bottom',
            text: ['高','低'],           // 文本，默认为数值文本
            calculable: true
        },

        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name:name,
                type:'pie',
                data:data_input.sort(function (a, b) { return a.value - b.value; }),
                roseType:'radius',
                radius : '55%',
                center: ['50%', '50%'],
                itemStyle: {
                    normal: {
                        // color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(0, 0, 0, 0.3)'

                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(0, 0, 0, 0.3)'

                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
                // center: ['50%', '50%'],
            }
        ]
    };
    myChart.setOption(option);
}

function initDateChart(data_liveInRate, data_bookNum) {
    pieChart_container.css("display", "none");
    dateChart_container.css("display", "block");

    myChart = echarts.init(document.getElementById('dateChart-container'));


    var data_y_liveInRate=[];
    var data_y_bookNum=[];
    var data_x =[];
    data_liveInRate.forEach(function (item) {
        data_x.push(item.name);
        data_y_liveInRate.push(item.value);
    });
    data_bookNum.forEach(function (item) {
        data_y_bookNum.push(item.value);
    });
    var colors = [
        '#5793f3',
        '#FFA039'];
    var option = {

        color: colors,

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        legend: {
            data:[NAME_LIVE_IN_RATE,NAME_BOOK_NUM]

        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: data_x
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: NAME_LIVE_IN_RATE,
                min: 0,
                max: 1,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                }
            },{
                show:false
            },
            {
                type: 'value',
                name: NAME_BOOK_NUM,
                min: 0,
                // max: 600,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[2]
                    }
                },
                axisLabel: {
                    formatter: '{value} 笔'
                }
            }
        ],
        series: [
            {
                name:NAME_LIVE_IN_RATE,
                type:'bar',
                data:data_y_liveInRate
            },
            {
                name:NAME_BOOK_NUM,
                type:'line',
                yAxisIndex: 2,
                data:data_y_bookNum
            }
        ]
    };
    myChart.setOption(option);

// Enable data zoom when user click bar.
    var zoomSize = 6;
    myChart.on('click', function (params) {
        console.log(data_x[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart.dispatchAction({
            type: 'dataZoom',
            startValue: data_x[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: data_x[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        });
    });
}


function show() {
    $.ajax({
        url:'/data/venue/getSeatedBookRate',
        async: false,
        success:function (data) {
            data_liveInRate=data;
            $.ajax({
                url:'/data/venue/getAllBookNum',
                async: false,
                success:function (data) {
                    data_bookNum=data;
                    initDateChart(data_liveInRate,data_bookNum);

                },
                error:function (data) {

                }
            });
        },
        error:function (data) {

        }
    });
}


function showRoomType() {
    $.ajax({
        url: '/data/venue/getAllBookNum/activityType',
        success: function (data) {
            initPieChart(data,NAME_ROOM_TYPE);
        },
        error:function (data) {

        }
    });
}


