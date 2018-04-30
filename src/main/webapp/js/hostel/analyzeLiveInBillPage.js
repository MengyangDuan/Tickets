/**
 * Created by disinuo on 17/3/13.
 */
var NAME_LIVEIN_VIP_RATE='入住会员率';
var NAME_LIVEIN_NUM='订单量';
var NAME_ROOM_PRICE='房价(/元)';
var NAME_ROOM_TYPE='房型';
var NAME_GUEST_TYPE='顾客类型';
var NAME_GUEST_AGE="顾客年龄"
var myChart=null;
var data_liveInVipRate=null;
var data_liveInNum=null;
var dateChart_container=null;
var pieChart_container=null;
var mapChart_container=null;
var weekChart_container=null;
var dayChart_container=null;
$(function () {

    dateChart_container=$('#dateChart-container');
    pieChart_container=$('#pieChart-container');
    mapChart_container=$('#mapChart-container');
    weekChart_container=$('#weekChart-container');
    dayChart_container=$('#dayChart-container');
    showYear();

});


$('#year').click(function (e) {
    showYear();
});

$('#roomType').click(function (e) {
    showRoomType();
});
$('#roomPrice').click(function (e) {
    showRoomPrice();
});
$('#guestType').click(function (e) {
   showGuestType();
});
$('#guestAge').click(function (e) {
    showGuestAge();
});

function initPieChart(data_input,name) {
    console.log('init room chart');
    console.log(data_input);
    dateChart_container.css("display", "none");
    weekChart_container.css("display", "none");
    pieChart_container.css("display", "block");
    mapChart_container.css("display", "none");
    dayChart_container.css("display", "none");

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
function initRegionChart(data_input) {
    dateChart_container.css("display", "none");
    weekChart_container.css("display", "none");
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "block");
    dayChart_container.css("display", "none");
    myChart = echarts.init(document.getElementById('mapChart-container'));

    var data_max=0;
    data_input.forEach(function (item) {
        if(item.value>data_max) data_max=item.value;
    });
    console.log(data_max);
    option = {
        title: {
            text: '订单量',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },

        visualMap: {
            min: 0,
            max: data_max,
            left: 'left',
            top: 'bottom',
            text: ['高','低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '订单量',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:data_input
            }

        ]
    };
    myChart.setOption(option);
}

function initDateChart(data_liveInNum,data_liveInVipRate) {
    dateChart_container.css("display", "block");

    weekChart_container.css("display", "none");
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dayChart_container.css("display", "none");

    myChart = echarts.init(document.getElementById('dateChart-container'));

    console.log('入住会员率');
    console.log(data_liveInVipRate);
    console.log('入住量');
    console.log(data_liveInNum);
    var data_y_liveInVipRate=[];
    var data_y_liveInNum=[];
    var data_x =[];
    data_liveInVipRate.forEach(function (item) {
        data_x.push(item.name);
        data_y_liveInVipRate.push(item.value);
    });
    data_liveInNum.forEach(function (item) {
        data_y_liveInNum.push(item.value);
    });
    var colors = [
        '#5793f3',
        // '#abcf2e',
        '#FFA039'];
    var option = {
        title: {
            // text: '入住情况',
            left: 'center',
        },
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
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:[NAME_LIVEIN_NUM,NAME_LIVEIN_VIP_RATE]
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
                name: NAME_LIVEIN_VIP_RATE,
                min: 0,
                max: 1,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                }

            },
            {
                type: 'value',
                name: NAME_LIVEIN_NUM,
                min: 0,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value} 笔'
                }
            }

        ],
        series: [
            {
                name:NAME_LIVEIN_NUM,
                type:'bar',
                yAxisIndex: 1,
                data:data_y_liveInNum
            },
            {
                name:NAME_LIVEIN_VIP_RATE,
                type:'line',
                data:data_y_liveInVipRate
            }
        ]
    };
    myChart.setOption(option);
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


function initWeekChart(data_liveInNum) {
    weekChart_container.css("display", "block");

    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dayChart_container.css("display", "none");
    dateChart_container.css("display", "none");

    myChart = echarts.init(document.getElementById('weekChart-container'));



    var data_y_liveInNum=[];
    var data_x =[];
    data_liveInNum.forEach(function (item) {
        data_x.push(item.name);
        data_y_liveInNum.push(item.value);
    });
    var option = {

        // color: colors,
        title: {
            text: '入住情况',
            left: 'center'
        },
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
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: {
            axisTick: {
                alignWithLabel: true
            },
            data: data_x
        },
        yAxis: {
        },
        series: [
            { // For shadow
                type: 'bar',
                itemStyle: {
                    normal: {color: 'rgba(0,0,0,0.05)'}
                },
                barGap:'-100%',
                barCategoryGap:'40%',
                data: data_x,
                animation: false
            },
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#2378f7'},
                                {offset: 0.7, color: '#2378f7'},
                                {offset: 1, color: '#83bff6'}
                            ]
                        )
                    }
                },
                data: data_y_liveInNum
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

function initDayChart(data_liveInNum) {
    dateChart_container.css("display", "none");
    weekChart_container.css("display", "none");
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dayChart_container.css("display", "block");
    myChart = echarts.init(document.getElementById('dayChart-container'));
    var hours = data_liveInNum.x;
    var days = data_liveInNum.y;

    var data = data_liveInNum.z;
    var data_larger=[];
    data.forEach(function (item) {
        var value=item[2];
        data_larger.push([item[0],item[1],value]);
    });
    option = {
        title: {
            text: '入住人数-时段图',
            left: 'center',
        },
        legend: {
            data: ['Punch Card'],
            left: 'right',
            textStyle:{
                // color:'#fff'
            }
        },
        polar: {},
        tooltip: {
            formatter: function (params) {
                return  days[params.value[0]]+":"+ hours[params.value[1]] +'<br>'+ params.value[2] + '人 入住';
            }
        },
        angleAxis: {
            type: 'category',
            data: hours,
            boundaryGap: false,
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#000',
                    type: 'dashed'
                }
            },
            axisLine: {
                show: false
            }
        },
        radiusAxis: {
            type: 'category',
            data: days,
            axisLine: {
                show: false
            },
            axisLabel: {
                rotate: 45
            }
        },
        series: [{
            name: 'Punch Card',
            type: 'scatter',
            coordinateSystem: 'polar',
            symbolSize: function (val) {
                return val[2] * 6;
            },
            data: data_larger,//TODO 为了效果暂时放大
            animationDelay: function (idx) {
                return idx * 5;
            }
        }]
    };
    myChart.setOption(option);

}


function showYear() {
    $.ajax({
        url:'/data/venue/getBookNum',
        async: false,
        success:function (data) {
            console.log(data);
            data_liveInNum=data;
            $.ajax({
                url:'/data/venue/getBookVipRate',
                async: false,
                success:function (data) {
                    console.log(data);
                    data_liveInVipRate=data;
                    initDateChart(data_liveInNum,data_liveInVipRate);
                },
                error:function (data) {
                    alert('ERROR');
                }
            });
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function showDay() {
    $.ajax({
        url:'/data/venue/getLiveInNum/hour',
        async: false,
        success:function (data) {
            console.log(data);
            initDayChart(data);
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}

function showRoomType() {
    $.ajax({
        url: '/data/hostel/getLiveInNum/room/type',
        success: function (data) {
            console.log(data);
            initPieChart(data,NAME_ROOM_TYPE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showRoomPrice() {
    $.ajax({
        url: '/data/hostel/getLiveInNum/room/price',
        success: function (data) {
            console.log(data);
            initPieChart(data,NAME_ROOM_PRICE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showGuestType() {
    $.ajax({
        url: '/data/hostel/getLiveInNum/guest/type',
        success: function (data) {
            console.log(data);
            initPieChart(data,NAME_GUEST_TYPE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
