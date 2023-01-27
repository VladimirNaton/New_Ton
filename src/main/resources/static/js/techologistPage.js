$(document).ready(function () {

    let idSelectedLeftTable = '';
    let searchData = '';
    let modalShow = false;
    let idSelectedProductTable = '';

    let leftProductTable = $('#left-product-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-technologist-left-table",
            "type": "POST",
            data: function (data) {
                data.orderColumn = data.order[0].column;
                data.orderType = data.order[0].dir;
                data.searchValue = searchData;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "idpr", "width": "10%"},
            {"data": "brend", "width": "25%"},
            {"data": "nameprod", "width": "65%"}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "orderable": false
            },
            {
                "targets": 1,
                "orderable": false
            },
            {
                "targets": 2,
                "orderable": false
            }
        ]
    });


    let rightProductTable = $('#right-product-table').DataTable({
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
                "targets": 0,
                "orderable": false
            },
            {
                "targets": 1,
                "orderable": false
            },
            {
                "targets": 2,
                "orderable": false
            },
            {
                "targets": 3,
                "orderable": false
            },
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
        if (!modalShow) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                let elem = $(this);
                idSelectedLeftTable = elem.find("td:first").html();
                getDataBySelectedCatalogRow(idSelectedLeftTable);
            } else {
                leftProductTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                let elem = $(this);
                idSelectedLeftTable = elem.find("td:first").html();
                getDataBySelectedCatalogRow(idSelectedLeftTable);
            }
        }
    });

    $('#right-product-table tbody').on('click', 'tr', function () {
        if (!modalShow) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                let elem = $(this);
                idSelectedProductTable = elem.find("td:first").html();
            } else {
                rightProductTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                let elem = $(this);
                idSelectedProductTable = elem.find("td:first").html();
            }
        }
    });

    $('#search-prod').click(function () {
        if (!modalShow) {
            searchData = $('#name-prod').val();
            leftProductTable.ajax.reload();
        }
    })

    $('#resetSearch').click(function () {
        $('#name-prod').val('');
        searchData = '';
        leftProductTable.ajax.reload();

    })


    function getDataBySelectedCatalogRow(idProd) {
        if (idSelectedLeftTable !== '') {
            $.ajax({
                url: '/search/get-data-by-selected-catalog-row?idProd=' + idProd,
                method: 'get',
                success: function (data) {
                    addDataOnInformationRow(data);
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });
        }
    }

    function addDataOnInformationRow(data) {
        $('#first-row-brend').val(data.brend);
        $('#first-row-date-production').val(data.dataCreate);
        $('#second-row-product-name').val(data.nameprod);
        $('#common-weight').val(data.mass);
    }

    $('#delete-recipe').click(function () {
        if (idSelectedProductTable !== '') {
            $('#delete-recipe-modal').show();
            modalShow = true;
        }
    })

    $('#close-delete-recipe-modal').click(function () {
        $('#delete-recipe-modal').hide();
        modalShow = false;
    })

    $('#delete-recipe-modal-button').click(function () {
        $.ajax({
            url: '/delete/selected-row-from-right-data-table-technologist-page',
            method: 'delete',
            data: {'idProd': idSelectedProductTable},
            success: function (data) {
                modalShow = false;
                idSelectedProductTable = '';
                $('#delete-recipe-modal').hide();
                rightProductTable.ajax.reload();
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
            }
        });
    })

    $('#edit-recipe').click(function () {
        if (idSelectedProductTable !== '') {
            let url = './edite-recipe?idProd=' + idSelectedProductTable;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })


})