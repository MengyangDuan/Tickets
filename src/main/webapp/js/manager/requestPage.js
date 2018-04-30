/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getRequestOpenList();
    getRequestModifyList();
});

function getRequestOpenList(){
    $('#requestTable').bootstrapTable({
        url: '/data/boss/getNonCheckedVenues',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'id',
            title: '场馆编号',
            align: 'center',
            sortable:true

        }, {
            field: 'venueName',
            title: '场馆名',
            align: 'center'
        }, {
            field: 'address',
            title: '场馆地址',
            align: 'center'
        },{
            field: 'seatMap',
            title: '座位情况',
            align: 'center',
        },{
            field:"",
            title:"审批",
            formatter:opOpenFormatter,
            events:operateEvents
        }]
    });
}

function getRequestModifyList(){
    $('#requestModifyTable').bootstrapTable({
        //TODO
        url: '/data/boss/getNonCheckedModifies',
        columns: [{
            field: 'venueId',
            title: '场馆编号',
            align: 'center',
        }, {
            field: 'name_original',
            title: '原场馆名',
            align: 'center',
        }, {
            field: 'address_original',
            title: '原地址',
            align: 'center'
        },{
            field: 'seat_original',
            title: '原座位',
            align: 'center',
        }, {
            field: 'name_new',
            title: '新场馆名',
            align: 'center',
        }, {
            field: 'address_new',
            title: '新地址',
            align: 'center'
        },{
            field: 'seat_new',
            title: '新座位',
            align: 'center',
        },{
            field:"",
            title:"审批",
            formatter:opModifyFormatter,
            events:operateEvents
        }]
    });
}

function opOpenFormatter() {
    return [
        '<a class="yes_open" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-ok"></i>',
        '</a>  ',
        '<a class="no_open" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

function opModifyFormatter() {
    return [
        '<a class="yes_modify" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-ok"></i>',
        '</a>  ',
        '<a class="no_modify" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}


var operateEvents = {
    'click .yes_open': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/checkNewVenue',
            data:{venueId:row.id,result:'true'},
            success:function (data) {
                swal('成功','审核信息更新成功','success');
                location.reload();
            }
        })
    },
    'click .no_open': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/checkNewVenue',
            data:{venueId:row.id,result:'false'},
            success:function (data) {
                swal('成功','审核信息更新成功','success');
                location.reload();
            }
        })
    },
    'click .yes_modify': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/checkModifyVenue',
            data:{modifyid:row.id,result:'true',email:row.email},
            success:function (data) {
                swal('成功','审核信息更新成功','success');
                location.reload();
            },
            error:function (data) {
                swal('失败','审核信息更新失败','error');
                location.reload();
            }
        })
    },
    'click .no_modify': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/checkModifyVenue',
            data:{modifyid:row.id,result:'false',email:row.email},
            success:function (data) {
                swal('成功','审核信息更新成功','success');
                location.reload();
            }
        })
    }
}
//  UNCHECKED,APPROVED,DENIED;