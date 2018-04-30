/**
 * Created by disinuo on 17/3/12.
 */
$(function () {
    //只有6号订单是真的是1号会员
    getRoomList();
});

function getRoomList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getRooms',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        // search:true,
        // searchText:'找一找心仪的客栈~',
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center',
            sortable:true

        }, {
            field: 'img',
            title: '',
            align: 'center',
            formatter:imgFormatter
        }, {
            field: 'name',
            title: '房型',
            align: 'center',
            sortable:true
        }, {
            field: 'price',
            title: '房价',
            align: 'center',
            formatter: moneyFormatter,
            sortable:true
        },{
            field: 'capacity',
            title: '容量',
            align: 'center',
            sortable:true

        },{
            field: 'totalNum',
            title: '房间数',
            align: 'center',
            sortable:true

        },{
            field: 'vacantNum',
            title: '空闲数',
            align: 'center',
            sortable:true

        },{
            field: 'bookedNum',
            title: '预订数',
            align: 'center',
            sortable:true
        },{
            field: 'startDate',
            title: '有效时段',
            align: 'center',
            formatter: periodFormatter
        },{
            field: 'state',
            title: '',
            align: 'center',
            formatter: stateFormatter

        },{
            field:'hostelId',
            title: '',
            align: 'center',
            formatter:operateFormatter,
            events: operateEvents,
            sortable:true

        }]
    });
}
function stateFormatter(value,row,index) {
    if(value==-1) return '<span class="label label-default">已下市</span>';
    else if(value==0) return '<span class="label label-success">有效</span>';
    else return '<span class="label label-default">未上市</span>';
}
function operateFormatter(value, row, index) {
     if(row.state==0){
         return [
             '<button class="modify btn btn-primary" ',
             'href="/hostel/modifyRoom?roomId=',
             row.id,
             '" >',
             '<i class="glyphicon glyphicon-pencil"></i></button>        ',
             '<button class="delete btn btn-primary" href="#" >',
             '<i class="glyphicon glyphicon-trash"></i></button>'
         ].join('');
     }else {
         return [
             '<button class="modify btn btn-primary" ',
             'href="/hostel/modifyRoom?roomId=',
             row.id,
             '" >',
             '<i class="glyphicon glyphicon-pencil"></i></button>        ',
             '<button class="disabled delete btn btn-default" href="#" >',
             '<i class="glyphicon glyphicon-trash"></i></button>'
         ].join('');
     }
}

window.operateEvents = {
    'click .delete':function (event, value, row, index){
        var ans=confirm("确定要让这个房间下市吗");
        if(ans){
            $.ajax({
                type:'POST',
                url:'/hostel/invalidateRoom',
                data:{roomId:row.id},
                success:function (data) {
                    location.reload();
                }
            });
        }
    },
    'click .modify':function (event,value,row,index) {
        location.replace('/hostel/modifyRoom?roomId='+row.id);
    }
};


function periodFormatter(value, row, index) {
    return value+" ~ "+row.endDate;
}