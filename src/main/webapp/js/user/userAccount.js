
$(document).ready(function () {
    initInfo();
})

function initInfo() {
    $.ajax({
        url:'/data/user/getUserAccount',
        success:function (data) {
            $('#userAccount').html(data.accountId);
            $('#userBalance').html(data.balance);
        },
        error:function (data) {
            $('#userAccount').html('0');
            $('#userBalance').html('0');
        }
    })
}