$(document).ready(function () {

    let idSelectedCatalogTable = '';
    let searchData = '';
    let modalShow = false;

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
            {"data": "idpr"},
            {"data": "strDate"},
            {"data": "brend"},
            {"data": "nameprod"},
            {"data": "percent"},
            {"data": "mass"},
            {"data": "tempprodmin"},
            {"data": "tempprodmax"}
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
            },
            {
                "targets": 7,
                "orderable": false
            }
        ]
    });

    $('#left-product-table tbody').on('click', 'tr', function () {
        if (!modalShow) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                idSelectedCatalogTable = '';
                resetDataFromInformationRow();
            } else {
                catalogProductTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                let elem = $(this);
                idSelectedCatalogTable = elem[0].cells[0].innerText;
                getDataBySelectedCatalogRow();
            }
        }
    });

    $('#search-prod').click(function () {
        searchData = $('#name-prod').val();
        if (searchData !== '') {
            catalogProductTable.ajax.reload();
        }
    })

    $('#resetSearch').click(function () {
        searchData = '';
        $('#name-prod').val('');
        catalogProductTable.ajax.reload();
    })

    function getDataBySelectedCatalogRow() {
        if (idSelectedCatalogTable !== '') {
            $.ajax({
                url: '/search/get-data-by-selected-catalog-row?idProd=' + idSelectedCatalogTable,
                success: function (data) {
                    $('#edite-recipe-head-brend').val(data.brend);
                    $('#edite-recipe-head-date-create').val(data.dataCreate);
                    $('#edite-recipe-head-name-prod').val(data.nameprod);
                    $('#common-weight-edite-recipe').val(data.mass);
                    $('#temp-min').val(data.tempprodmin);
                    $('#temp-max').val(data.tempprodmax);
                    $('#control').val(data.percent);
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

    $('#save-recipe').click(function () {
        if (idSelectedCatalogTable !== '') {
            let data = {
                "id": idSelectedCatalogTable,
                "datecr": $('#edite-recipe-head-date-create').val(),
                "brend": $('#edite-recipe-head-brend').val(),
                "nameprod": $('#edite-recipe-head-name-prod').val(),
                "mass": $('#common-weight-edite-recipe').val(),
                "percent": $('#control').val(),
                "tempprodmin": $('#temp-min').val(),
                "tempprodmax": $('#temp-max').val()
            }

            $.ajax({
                url: '/update/update-selected-catalog-row',
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data) {
                        idSelectedCatalogTable = '';
                        catalogProductTable.ajax.reload();
                        resetDataFromInformationRow();
                        alert("Данные успешно обновлены !!!");
                    } else {
                        alert("Возникла ошибка при обновлении данных в каталоге !!!");
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

    })

    function resetDataFromInformationRow() {
        $('#edite-recipe-head-brend').val('');
        $('#edite-recipe-head-date-create').val('');
        $('#edite-recipe-head-name-prod').val('');
        $('#common-weight-edite-recipe').val('');
        $('#temp-min').val('');
        $('#temp-max').val('');
        $('#control').val('');

    }


    $('#delete-recipe').click(function () {
        if (idSelectedCatalogTable !== '') {
            $('#delete-recipe-modal').show();
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

    $('#close-delete-recipe-modal').click(function () {
        $('#delete-recipe-modal').hide();
    })

    $('#delete-recipe-modal-button').click(function () {
        deleteSelectedCatalogRow();
    })

    function deleteSelectedCatalogRow() {
        $.ajax({
            url: '/update/delete-selected-catalog-row/' + idSelectedCatalogTable,
            method: 'put',
            success: function (data) {
                if (data) {
                    idSelectedCatalogTable = '';
                    catalogProductTable.ajax.reload();
                    resetDataFromInformationRow();
                    $('#delete-recipe-modal').hide();
                    alert("Рецепт успешно удален !!!");
                } else {
                    $('#delete-recipe-modal').hide();
                    alert("Возникла ошибка при удалении рецепта в каталоге !!!");
                }
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
            }
        });
    }

    $('#add-recipe').click(function () {
        let brend = $('#edite-recipe-head-brend').val();
        let nameProd = $('#edite-recipe-head-name-prod').val();
        let weight = $('#common-weight-edite-recipe').val();
        let dateCreate = $('#edite-recipe-head-date-create').val();
        let percent = $('#control').val();
        let tempprodmin = $('#temp-min').val();
        let tempprodmax = $('#temp-max').val();


        if (idSelectedCatalogTable === '') {
            if (brend !== '' && nameProd !== '' && weight !== '') {
                let data = {
                    "brend": brend,
                    "nameprod": nameProd,
                    "mass": weight,
                    "datecr": dateCreate,
                    "percent": percent,
                    "tempprodmin": tempprodmin,
                    "tempprodmax": tempprodmax
                }
                $.ajax({
                    url: '/update/add-new-recipe',
                    method: 'put',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(data),
                    success: function (data) {
                        if (data) {
                            catalogProductTable.ajax.reload();
                            resetDataFromInformationRow();
                            alert("Данные успешно сохранены !!!");
                        } else {
                            alert("Возникла ошибка при добавлении данных в каталог !!!");
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
                alert("Поля Бренд, Название продукта, Вес, обязательны для заполнения !!!");
            }

        } else {
            alert("Для редактирования продукта нажмите кнопку 'Сохранить' !!!");
        }
    })

    $('#directory-editor').click(function () {
        let url = './edite-recipe-catalog-page';
        window.open(url, '_blank');
    })

    $('#edit-recipe').click(function () {
        if (idSelectedCatalogTable !== '') {
            let url = './edite-recipe-catalog-page?idCat=' + idSelectedCatalogTable;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })


})