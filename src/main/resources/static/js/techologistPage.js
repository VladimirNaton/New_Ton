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
            {"data": "idpr", "width": "10%"},
            {"data": "brend", "width": "25%"},
            {"data": "nameprod", "width": "65%"}
        ]
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
            {"data": "dateCreate"},
            {"data": "percent"},
            {"data": "mass"},
            {"data": "comment"}
        ],
        "columnDefs": [
            {
                "targets": 4,
                "orderable": false
            },
            {
                "targets": 5,
                "orderable": false
            },
            {
                "targets": 6,
                "orderable": false
            }
        ]
    });

    $('#left-product-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            let elem = $(this);
            var idSelected = elem.find("td:first").html();
        } else {
            leftProductTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            var idSelected = elem.find("td:first").html();
        }
    });

    $('#right-product-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            rightProductTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });


})