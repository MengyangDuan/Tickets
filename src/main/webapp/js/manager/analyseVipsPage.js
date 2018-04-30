
var NAME_VIP_LEVEL="会员等级分布";
var myChart=null;

var levelChart_container=null;
var monthChart_container=null;


$(document).ready(function () {
    levelChart_container=$('#levelChart-container');
    monthChart_container=$('#monthChart-container');
    showVipLevel();
});

$('#vipLevel').click(function () {
    showVipLevel();
});

$('#guest').click(function () {
    showGuest();
});

function showGuest() {
    $.ajax({
        url:'/data/boss/getGuestNum/month',
        success:function (data) {
            console.log(data);
            initMonthChart(data);
        },
        error:function (data) {
            alert('ERROR');
            console.log(data);
        }
    });
}

function showVipLevel() {
    $.ajax({
        url:'/data/boss/getVipNum/level',
        success:function (data) {
            console.log(data);
            initBar_PieChart(data,NAME_VIP_LEVEL)
        }
    });
}

function initMonthChart(data) {
    monthChart_container.css("display", "block");
    levelChart_container.css("display", "none");
    myChart = echarts.init(document.getElementById('monthChart-container'));

    var app = {};
    var option = null;
    var dataMap = {};
    function dataFormatter(obj) {
        var pList = ['1月','2','3','4','5','6','7','8','9','10','11','12月'];
        var temp= obj[2018];
        var max = 0;
        var sum = 0;
        for (var i = 0, l = temp.length; i < l; i++) {
            max = Math.max(max, temp[i]);
            sum += temp[i];
            obj[2018][i] = {
                name : pList[i],
                value : temp[i]
            }
        }
        obj[2018 + 'max'] = Math.floor(max / 100) * 100;
        obj[2018 + 'sum'] = sum;
        return obj;
    }


    dataMap.dataSI = dataFormatter(data.unvip);
    dataMap.dataTI = dataFormatter(data.vip);
    console.log('dataMap');
    console.log(dataMap);
    option = {
        baseOption: {
            timeline: {
                // y: 0,
                axisType: 'category',
                // realtime: false,
                // loop: false,
                autoPlay: true,
                // currentIndex: 2,
                playInterval: 3000,
                label: {
                    formatter : function(s) {
                        return (new Date(s)).getFullYear();
                    }
                }
            },
            tooltip: {
            },
            legend: {
                x: 'right',
                data: ['非会员', '会员'],
            },
            calculable : true,
            grid: {
                top: 80,
                bottom: 100,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true,
                            formatter: function (params) {
                                return params.value.replace('\n', '');
                            }
                        }
                    }
                }
            },
            xAxis: [
                {
                    'type':'category',
                    'axisLabel':{'interval':0},
                    'data':['1月','2','3','4','5','6','7','8','9','10','11','12月'],
                    splitLine: {show: false}
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '住店人次（/人次）'
                }
            ],
            series: [
                {name: '非会员', type: 'bar'},
                {name: '会员', type: 'bar'},
                {
                    name: '会员占比',
                    type: 'pie',
                    center: ['75%', '35%'],
                    radius: '28%',
                    z: 100
                }
            ]
        },
        options: [
            {
                title : {text: '2018住店人次统计'},
                series : [
                    {data: dataMap.dataSI['2018']},
                    {data: dataMap.dataTI['2018']},
                    {data: [
                        {name: '非会员', value: dataMap.dataSI['2018sum']},
                        {name: '会员', value: dataMap.dataTI['2018sum']}
                    ]}
                ]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}



function initBar_PieChart(data,name) {
    levelChart_container.css("display", "block");
    monthChart_container.css("display","none");
    myChart = echarts.init(document.getElementById('levelChart-container'));

    var app = {};
    app.title = name;


    var waterMarkText = 'ECHARTS';

    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
    canvas.width = canvas.height = 100;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.globalAlpha = 0.08;
    ctx.font = '20px Microsoft Yahei';
    ctx.translate(50, 50);
    ctx.rotate(-Math.PI / 4);
    ctx.fillText(waterMarkText, 0, 0);

    var option = {
        backgroundColor: {
            type: 'pattern',
            image: canvas,
            repeat: 'repeat'
        },
        tooltip: {},
        title: {
            text: '会员等级',
            subtext: '总计 ' + data.total,
        },
        grid: {
            top: 50,
            width: '50%',
            bottom: '45%',
            left: 10,
            containLabel: true
        },
        xAxis: {
            type: 'value',
            max: data.total,
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            data: Object.keys(data.level),
            axisLabel: {
                interval: 0,
                rotate: 30
            },
            splitLine: {
                show: false
            }
        },
        series: [{
            type: 'bar',
            stack: 'chart',
            z: 3,
            label: {
                normal: {
                    position: 'right',
                    show: true
                }
            },
            data: Object.keys(data.level).map(function (key) {
                return data.level[key];
            })
        }, {
            type: 'bar',
            stack: 'chart',
            silent: true,
            itemStyle: {
                normal: {
                    color: '#eee'
                }
            },
            data: Object.keys(data.level).map(function (key) {
                return data.total - data.level[key];
            })
        }, {
            type: 'pie',
            radius: [0, '30%'],
            center: ['75%', '25%'],
            data: Object.keys(data.level).map(function (key) {
                return {
                    name: key.replace('.js', ''),
                    value: data.level[key]
                }
            })
        }]
    }
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

