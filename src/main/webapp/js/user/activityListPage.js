
$(document).ready(function () {
    getActivityList();
});

function getActivityList() {
    $('#table').bootstrapTable({
        url: '/data/user/getPreActivityList',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'img',
            title: '',
            align: 'center',
            formatter:imgFormatter_vip
        }, {
            field: 'name',
            title: '活动名',
            align: 'center',
            formatter: operateFormatter,
            sortable:true
        }, {
            field: 'showType',
            title: '分类',
            align: 'center',
            sortable:true
        }, {
            field: 'time',
            title: '时间',
            align: 'center',
            formatter: timeFormatter,
            sortable:true
        },{
            field: 'place',
            title: '地址',
            align: 'center',
        }]
    });
}


function imgFormatter_vip(value,row,index) {
    return [
        '<img src="../../img/poster'+row.id%2+'.jpeg" alt="图片" class="image-little"/>'
    ].join('');
}
function operateFormatter(value, row, index) {
    return [
        '<a href="/user/activityInfo?activityId=',
        row.id,
        '">',
        value,
        '</a>'
        ].join('');
}

function timeFormatter(value,row,index) {
    return new Date(parseFloat(value)).format("yyyy-MM-dd hh:mm:ss");
}

