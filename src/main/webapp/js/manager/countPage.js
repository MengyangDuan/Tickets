/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getMoneyList();
});

function getMoneyList(){
    $('#moneyTable').bootstrapTable({
        url: '/data/boss/getUnhandledAccount',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'venueId',
            title: '场馆编号',
            align: 'center',
            sortable:true

        },  {
            field: 'venueName',
            title: '场馆名',
            align: 'center'
        }, {
            field: 'timestamp',
            title: '时间',
            align: 'center',
            formatter:timeFormatter,
            sortable:true
        }, {
            field: 'money',
            title: '未结算金额',
            align: 'center',
            formatter:moneyCounter,
            sortable:true

        }]
    });
}
$('#countForm').submit(function (e) {
    $.ajax({
        type:'POST',
        url:'/boss/settleAccount',
        success:function (data) {
            alert(data);
            location.reload();
        }
    });
    e.preventDefault(); // avoid to execute the actual submit of the form.
})

function moneyCounter(value,row,index) {
   total=total+value;
    $('#total').html(total.toFixed(1));
   return value;
}
var total=0;

function timeFormatter(value,row,index) {
    return new Date(parseFloat(value)).format("yyyy-MM-dd hh:mm:ss");
}