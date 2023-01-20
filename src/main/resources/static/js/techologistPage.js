$(document).ready(function () {

    var leftProductTable = $('#left-product-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-technologist-left-table",
            "type": "POST",
            data: function (data) {
                data.orderColumn = data.order[0].column;
                data.orderType = data.order[0].dir;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "idpr"},
            {"data": "brend"},
            {"data": "nameprod"}
        ],
    });


    var rightProductTable = $('#right-product-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-technologist-right-table",
            "type": "POST",
            data: function (data) {
                data.orderColumn = data.order[0].column;
                data.orderType = data.order[0].dir;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "idpr"},
            {"data": "brend"},
            {"data": "nameprod"},
            {"data": "datecr"},
            {"data": "percent"},
            {"data": "mass"}
        ],
        "columnDefs": [
            {
                "targets": 4,
                "orderable": false
            },
            {
                "targets": 5,
                "orderable": false
            }
        ]
    });


})