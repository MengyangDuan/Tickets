/**
 * Created by disinuo on 17/3/13.
 */
var NAME_INCOME='总收入';
var NAME_VIP_INCOME='会员收入';
var myChart=null;
var incomeChart_container=null;
var avgChart_container=null;
$(function () {
    incomeChart_container=$('#incomeChart-container');
    avgChart_container=$('#avgChart-container');
    vip_income();
});

$('#year').click(function (e) {
    vip_income();
});
$('#avg_year').click(function (e) {
    avg();
});


function initAvg_Income_DateChart(data_incomeAvg) {
    incomeChart_container.css("display", "none");
    avgChart_container.css("display", "block");

    myChart = echarts.init(document.getElementById('avgChart-container'));



    var data_y_incomeAvg=[];
    var data_x =[];
    data_incomeAvg.forEach(function (item) {
        data_x.push(item.name);
        data_y_incomeAvg.push(item.value);
    });
    var option = {
        title:{
            text:'人均消费',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#000'
            }
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
            axisLabel: {
                formatter: '{value} 元'
            }
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
                data: data_y_incomeAvg
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

function initVip_Income_DateChart(data_income) {
    avgChart_container.css("display", "none");
    incomeChart_container.css("display", "block");

    myChart = echarts.init(document.getElementById('incomeChart-container'));

    var data_y_income=[];
    var data_y_vip_income=[];
    var data_x =[];
    var max=0;
    data_income.forEach(function (item) {
        data_x.push(item[0]);
        data_y_vip_income.push(item[1]);
        data_y_income.push(item[2]);
        if(item[2]>max) max=item[2];
    });
    var colors = [
        '#5793f3',
        '#abcf2e'];
    var option = {
        title:{
            text:'会员收入/总收入',
            top: 10,
            textStyle: {
                color: 'rgba(0,0,0,0.5)',
                fontSize:10
            }
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
            data:[NAME_INCOME,NAME_VIP_INCOME]

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
                name: NAME_INCOME,
                min: 0,
                max:max,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}元'
                }
            },{
                type: 'value',
                name: NAME_VIP_INCOME,
                min: 0,
                max: max,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
        ],
        series: [
            {
                name:NAME_INCOME,
                type:'bar',
                data:data_y_income
            },
            {
                name:NAME_VIP_INCOME,
                type:'bar',
                yAxisIndex: 1,
                data:data_y_vip_income
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


function avg() {
    $.ajax({
        url:'/data/venue/getIncomeAvg',
        async: false,
        success:function (data) {
            console.log(data);
            initAvg_Income_DateChart(data);

        },
        error:function (data) {
            alert('ERROR');
        }
    });
}


function vip_income() {
    $.ajax({
        url:'/data/venue/getMoneyVipRate',
        async: false,
        success:function (data) {
            console.log(data);
            initVip_Income_DateChart(data);


        },
        error:function (data) {
            alert('ERROR');
        }
    });
}

