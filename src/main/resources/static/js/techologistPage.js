$(document).ready(function () {

    let idSelectedCatalogTable = '';
    let searchData = '';
    let modalShow = false;
    let idSelectedProductTable = '';

    let catalogProductTable = $('#left-product-table').DataTable({
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


    let mainProductTable = $('#right-product-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-technologist-right-table",
            "type": "POST",
            data: function (data) {
                data.orderColumn = data.order[0].column;
                data.orderType = "desc";
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
                idSelectedCatalogTable = '';
                $('#first-row-brend').val('');
                $('#first-row-date-production').val('');
                $('#second-row-product-name').val('');
                $('#common-weight').val('');
            } else {
                catalogProductTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                let elem = $(this);
                idSelectedCatalogTable = elem.find("td:first").html();
                getDataBySelectedCatalogRow(idSelectedCatalogTable);
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
                mainProductTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                let elem = $(this);
                idSelectedProductTable = elem.find("td:first").html();
            }
        }
    });

    $('#search-prod').click(function () {
        if (!modalShow) {
            searchData = $('#name-prod').val();
            idSelectedCatalogTable = '';
            catalogProductTable.ajax.reload();
            removeDataFromInformationRows();
        }
    })

    $('#resetSearch').click(function () {
        $('#name-prod').val('');
        searchData = '';
        idSelectedCatalogTable = '';
        catalogProductTable.ajax.reload();
        removeDataFromInformationRows();
    })


    function getDataBySelectedCatalogRow(idProd) {
        if (idSelectedCatalogTable !== '') {
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
                mainProductTable.ajax.reload();
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
        if (!modalShow) {
            if (idSelectedProductTable !== '') {
                let url = './edite-recipe?idProd=' + idSelectedProductTable;
                window.open(url, '_blank');
            } else {
                alert("Вы не выбрали ни одной записи !!!");
            }
        }
    })

    $('#move-to-production').click(function () {
        if (!modalShow) {
            if (idSelectedCatalogTable !== '') {
                $.ajax({
                    url: '/update/move-catalog-row-to-main/' + idSelectedCatalogTable,
                    method: 'put',
                    success: function (data) {
                        if (data) {
                            idSelectedCatalogTable = '';
                            mainProductTable.ajax.reload();
                        } else {
                            alert("Возникла ошибка при передаче продукта в производство !!!");
                        }
                    },
                    beforeSend: function () {
                    },
                    complete: function () {
                    },
                    error: function (xhr, status, error) {
                    }
                });
            } else {
                alert("Вы не выбрали ни одной записи !!!");
            }
        }
    })

    function removeDataFromInformationRows() {
        $('#first-row-brend').val('');
        $('#first-row-date-production').val('');
        $('#second-row-product-name').val('');
        $('#common-weight').val('');
    }

    $('#send-teller').click(function () {
        if (!modalShow) {
            if (idSelectedProductTable !== '') {
                let days = $('#return-day').find('option:selected').val();
                let data = {
                    id: idSelectedProductTable,
                    days: days
                }
                $.ajax({
                    url: '/update/send-product-to-teller',
                    method: 'put',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(data),
                    success: function (data) {
                        if (data) {
                            if (data) {
                                idSelectedCatalogTable = '';
                                mainProductTable.ajax.reload();
                            }
                        } else {
                            alert("Возникла ошибка при передаче продукта в производство !!!");
                        }
                    },
                    beforeSend: function () {
                    },
                    complete: function () {
                    },
                    error: function (xhr, status, error) {
                    }
                });
            } else {
                alert("Вы не выбрали ни одной записи !!!");
            }
        }
    })


    $('#return-from-recipe').click(function () {
        if (idSelectedProductTable !== '') {
            $.ajax({
                url: '/update/update-data-by-catalog-from-main/' + idSelectedProductTable,
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    if (data) {
                        alert("Данные успешно обновленны !!!");
                    } else {
                        alert("Возникла ошибка при обновлении данных !!!");
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });

        } else {
            alert("Вы не выбрали рецепт для обновления в каталоге !!!");
        }

    })


})