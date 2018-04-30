

$(document).ready(function () {
    getOrderList();
});

$('#btn_nopay').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/user/getNoPayOrders'});
});
$('#btn_pre').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/user/getPreOrders'});
});
$('#btn_cousumed').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/user/getCousumedOrders'});
});
$('#btn_cancel').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/user/getCancelOrders'});
});
function getOrderList() {
    $('#table').bootstrapTable({
        url: '/data/user/getAllOrders',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [
            {
                field: 'orderTime',
                title: '下单时间',
                align: 'center',
                formatter:timeFormatter,
                sortable:true
            },{
                field: 'id',
                title: '订单号',
                align: 'center',
                formatter:operateFormatter,
                sortable:true

            },{
                field: 'activityName',
                title: '活动名',
                align: 'center',
                sortable:true

            },
            {
                field: 'activityTime',
                title: '活动时间',
                align: 'center',
                formatter:timeFormatter,
                sortable:true

            },{
                field: 'afterDiscountPrice',
                title: '会员价',
                align: 'center',
                formatter:moneyFormatter,
                sortable:true

            },{
                field: 'matchingStatus',
                title: '出票状态',
                align: 'center',
                formatter:stateFormatter,
            }, {
                field:'status',
                title:'',
                formatter:unbookFormatter,
                events:unbookEvents,
                sortable:true

            }]
    });
}
function operateFormatter(value, row, index) {
    return [
        '<a href="/vip/rooms?hostelId=',
        row.hostelId,
        '">',
        value,
        '</a>'
    ].join('');
}

function unbookFormatter(value,row,index) {
    if(value==2||value==3||value==4){
        return '<button id="unbookBtn" type="button" class="btn btn-default disabled">已取消</button>'
    }
    else if(value==1){
        return '<button id="unbookBtn" type="button" class="unbook btn btn-danger">取消</button>';
    } else if(value==5){
        return '<button id="unbookBtn" type="button" class="btn btn-primary disabled">已消费</button>'
    }
    else{
        return '<button id="unbookBtn" type="button" class="unbook btn btn-primary ">付款</button>'
    }
}

var unbookEvents = {
    'click .unbook': function (event, value, row, index) {
        if(value==1) {
            swal({
                title: "确定取消吗？",
                text: "你将根据相关退订退得部分金额！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定取消！",
                closeOnConfirm: false },
                function(){
                    $.ajax({
                        url: '/user/cancelOrder',
                        type: 'POST',
                        data: {orderId: row.id},
                        success: function (data) {
                            swal("取消！", "您的订单已取消。", "success");
                        }
                    })
            });
        }
        else if(value==0){
            location.href="/user/payOrder?orderId="+row.id+"&money="+row.afterDiscountPrice;
        }

    }
}

function stateFormatter(value,row,index) {
    var state=row.status;
    if(state==2||state==3||state==4)
        return'已退';
    else if(state==5)
        return'已消费';
    else{
        if(value==-1||value==1)
            return'已出票';
        else
            return'待配票';

    }
}

function timeFormatter(value,row,index) {
    return new Date(parseFloat(value)).format("yyyy-MM-dd hh:mm:ss");
}
