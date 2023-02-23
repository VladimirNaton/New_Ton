$(document).ready(function () {

    let idSelectedItem = '';

    let dissolversTable = $('#dissolvers-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": "/search/data-for-dissolvers-table",
            "type": "POST",
            data: function (data) {
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "eq"},
            {"data": "code"}
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

    $('#dissolvers-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            idSelectedItem = '';
            cleanInformationRow();
        } else {
            dissolversTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelectedItem = elem[0].cells[0].innerText;
            getDataForSelectedRow()
        }
    });

    function getDataForSelectedRow() {
        if (idSelectedItem !== '') {
            $.ajax({
                url: '/search/get-data-for-selected-row-dissolvers-table/' + idSelectedItem,
                method: 'get',
                success: function (data) {
                    $('#information-row-name').val(data.eq)
                    $('#information-row-code').val(data.code)
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

    function cleanInformationRow() {
        $('#information-row-name').val('')
        $('#information-row-code').val('')
    }

    $('#delete-item').click(function () {
        if (idSelectedItem !== '') {
            $.ajax({
                url: '/update/delete-selected-dissolver-row/' + idSelectedItem,
                method: 'delete',
                success: function (data) {
                    if (data) {
                        alert("Данные успешно удаленны !!!");
                        idSelectedItem = '';
                        dissolversTable.ajax.reload();
                        cleanInformationRow();
                    } else {
                        alert("Возникла ошибка при удалении данных !!!");
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

    $('#add-item').click(function () {
        let eq = $('#information-row-name').val();
        let code = $('#information-row-code').val();
        let data = {
            "eq": eq,
            "code": code
        }
        if (idSelectedItem === '') {
            $.ajax({
                url: '/update/create-new-cateq-row',
                method: 'post',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data) {
                        alert("Данные успешно добавлены !!!");
                        dissolversTable.ajax.reload();
                        cleanInformationRow();
                    } else {
                        alert("Возникла ошибка при добавлении данных !!!");
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
            alert("Для обновления данных нажмите кнопку Сохранить !!!");
        }
    })

    $('#save-item').click(function () {
        let eq = $('#information-row-name').val();
        let code = $('#information-row-code').val();
        if (idSelectedItem !== '') {
            let data = {
                "eq": eq,
                "code": code,
                "id": idSelectedItem
            }

            $.ajax({
                url: '/update/update-cateq-row',
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data) {
                        alert("Данные успешно добавлены !!!");
                        idSelectedItem = '';
                        dissolversTable.ajax.reload();
                        cleanInformationRow();
                    } else {
                        alert("Возникла ошибка при добавлении данных !!!");
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
            alert("Для добавления новых данных нажмите кнопку Добавить !!!");
        }
    })


})

