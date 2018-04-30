/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
    getPayBillList();
    getTotalIncome();

});

$('#btn_week').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getRecentPayList/week'});
});
$('#btn_month').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getRecentPayList/month'});
});
$('#btn_year').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getRecentPayList/year'});
});
$('#btn_all').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getAllPayList'});
});
$('#btn_unCounted').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getUncountedPayList'});
});
function getPayBillList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getAllPayList',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center',
            sortable:true

        },{
            field: 'createDate',
            title: '时间',
            align: 'center',
            sortable:true

        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            formatter:roomFormatter,
            sortable:true

        },{
            field: 'userRealName',
            title: '住户真名',
            align: 'center',
            formatter:guestFormatter

        },{
            field: 'money',
            title: '收入',
            align: 'center',
            formatter:moneyFormatter,
            sortable:true

        }]
    });
}
function getTotalIncome() {
    $.ajax({
        url:'/data/hostel/getIncome',
        success:function (data) {
            $('#value').html(data+"元");
        }
    })
}
