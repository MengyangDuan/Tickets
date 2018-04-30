/**
 * Created by disinuo on 17/3/14.
 */

$(document).ready(function () {
    getLiveBillList();
    getTotal();

});

function getLiveBillList() {
    hostelId=requestParamFormatter()['hostelId'];

    $('#table').bootstrapTable({
        url: '/data/boss/hostel/getLiveList?hostelId='+hostelId,
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'date',
            title: '时间',
            align: 'center',
            sortable:true

        }, {
            field: 'roomName',
            title: '房型',
            align: 'center'
            // events: operateEvents
        }, {
            field: 'roomPrice',
            title: '房价',
            align: 'center',
            sortable:true,
            formatter:moneyFormatter
        },{
            field: 'vipId',
            title: '会员编号',
            align: 'center',
            sortable:true

        },{
            field: 'userRealName',
            title: '住户真名',
            align: 'center'
        },{
            field: 'idCard',
            title: '住户身份证',
            align: 'center'
        },{
            field: 'inHostel',
            title: '',
            align: 'center',
            formatter: typeFormatter,
            sortable:true

        }]
    });
}
function typeFormatter(value,row,index) {
    if(value==true) return '<span class="label label-success">未离店</span>';
    else return '<span class="label label-default">已离店</span>';
}
function getTotal(){
    $.ajax({
        url:'/data/boss/hostel/getTotalLiveInNum?hostelId='+hostelId,
        success:function (data) {
            $('#total').html(data);
        }
    });
    $.ajax({
        url:'/data/boss/hostel/getPresentLiveInNum?hostelId='+hostelId,
        success:function (data) {
            $('#now').html(data);
        }
    })
}

var hostelId=null;