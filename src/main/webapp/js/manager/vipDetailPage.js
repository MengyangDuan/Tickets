/**
 * Created by disinuo on 17/3/14.
 */

$(document).ready(function () {
    vipId=requestParamFormatter()['vipId'];
    getPayBillList();
    getBookBillList();
});
function getPayBillList() {

    $('#payTable').bootstrapTable({
        url: '/data/boss/vip/getPayList?vipId='+vipId,
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'createDate',
            title: '时间',
            align: 'center',
        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            // events: operateEvents
        }, {
            field: 'money',
            title: '消费',
            align: 'center'
        },{
            field: 'hostelId',
            title: '客栈编号',
            align: 'center',
        },{
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
        }]
    });
}
function getBookBillList() {
    $('#bookTable').bootstrapTable({
        url: '/data/boss/vip/getBookList?vipId='+vipId,
        columns: [{
            field: 'id',
            title: '订单号',
            align: 'center',
        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
        }, {
            field: 'roomPrice',
            title: '房价',
            align: 'center'
        },{
            field: 'liveInDate',
            title: '入住时间',
            align: 'center',
        },{
            field: 'checkOutDate',
            title: '离店时间',
            align: 'center',
        },{
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
        },{
            field: 'hostelId',
            title: '客栈编号',
            align: 'center',
        }],
    });
}


function typeFormatter(value,row,index) {
    if(value==true) return '<span class="label label-success">住店</span>';
    else return '<span class="label label-warning">离店</span>';
}
function getTotal(){
    $.ajax({
        url:'/data/boss/hostel/getLiveInNum?hostelId='+hostelId,
        success:function (data) {
            $('#total').html(data);
        }
    })
}

var viplId=null;