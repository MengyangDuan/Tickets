/**
 * Created by disinuo on 17/5/28.
 */

$(document).ready(function () {
    getPayList();
});

function getPayList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getMoneyRecord',
        pagination:true,
        height:TABLE_HEIGHT,
        search:true,
        columns: [{
            field: 'date',
            title: '时间',
            align: 'center',
            sortable:true
        },{
            field: 'money',
            title: '',
            align: 'center',
            formatter:moneyRecordFormatter,
            sortable:true

        }, {
            field: 'typeStr',
            title: '类型',
            align: 'center',
            sortable:true

        }]
    });
}

function moneyRecordFormatter(value, row, index) {
    console.log(value);
    if(row.typeStr=='结算'){
        return ['<span class="label label-primary">+',
            value,
            '</span>'
        ].join('');
    } else {
        return ['<span class="label label-warning">+',
            value,
            '</span>'
        ].join('');
    }
}