$(document).ready(function () {

    var leftProductTable = $('#left-product-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-technologist-left-table",
            "type": "POST",
            data: function (data) {
                orderColumn = data.order[0].column;
                orderType = data.order[0].dir;

                data.orderColumn = data.order[0].column;
                data.orderType = data.order[0].dir;
                // data.typeDate = typeDate;
                // data.startDate = startDate;
                // data.endDate = endDate;
                // data.brend = brend;
                // data.productName = productName;
                // data.specification = specification;
                // data.requestFlag = 'request';

            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "idpr"},
            {"data": "brend"},
            {"data": "nameprod"}
        ],
    });


})