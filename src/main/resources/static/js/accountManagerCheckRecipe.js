$(document).ready(function () {
    let idMain = '';

    idMain = $('#id-edite-recipe').text();


    let editeRecipeTable = $('#edite-recipe-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": "/search/data-for-edite-recipe-table",
            "type": "POST",
            data: function (data) {
                data.idMain = $('#id-edite-recipe').text();
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "n"},
            {"data": "stage"},
            {"data": "code"},
            {"data": "nameraw"},
            {"data": "percent"},
            {"data": "mass"},
            {"data": "devper"},
            {"data": "devmass"},
            {"data": "turnmix"},
            {"data": "timemix"},
            {"data": "pastpart"},
            {"data": "strDate"},
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
            },
            {
                "targets": 8,
                "orderable": false
            }
        ]
    });


    $('#edite-recipe-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            editeRecipeTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
        }
    });

    function getDataForHeaderInformationString() {
        if (idMain !== '') {
            $.ajax({
                url: '/search/get-data-for-head-string-edite-recipe/' + idMain,
                method: 'get',
                success: function (data) {
                    $('#edite-recipe-head-brend').val(data.brend);
                    $('#edite-recipe-head-date-create').val(data.dateString);
                    $('#edite-recipe-head-name-prod').val(data.nameProd);
                    $('#temp-min').val(data.tempMin);
                    $('#temp-max').val(data.tempMax);
                    $('#common-weight-edite-recipe').val(data.mass);
                    $('#comment-account-manager').val(data.comment);
                    checkCommonPercents();
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

    getDataForHeaderInformationString();

    editeRecipeTable.on('draw', function () {
        checkCommonPercents();
    });

    function getTableData() {
        let tableData = editeRecipeTable.rows().data();
        return tableData;
    }

    function calculatePercents() {
        let data = getTableData();
        let mass = 0;
        $.each(data, function (key, value) {
            mass += value.mass;
        });

        let commonWeight = Number($('#common-weight-edite-recipe').val()) / 100;
        return (mass / commonWeight).toFixed(2);
    }

    function calculateMassElements(data) {
        let mass = 0;
        $.each(data, function (key, value) {
            mass += value.mass;
        });
        return mass;

    }

    function checkCommonPercents() {
        let tableData = getTableData();
        let percents = calculatePercents(tableData);
        $('#control').val(percents);
        changeColorCommonPercentsInput(percents);
    }

    function changeColorCommonPercentsInput(percents) {
        if (percents < 100 || percents > 100) {
            $('#control').css('background-color', 'crimson');
        } else {
            $('#control').css('background-color', 'white');
        }
    }

    $('#return-to-technologist').click(function () {
        if (idMain !== '') {
            let data = {
                'idMain': idMain,
                'comment': $('#comment-account-manager').val()
            }
            $.ajax({
                url: '/update/return-recipe-to-technologist',
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
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
                    alert("Возникла ошибка при обновлении данных !!!");
                }
            });
        }
    })

    $('#send-to-production').click(function () {
        if (idMain !== '') {
            let data = {
                'idMain': idMain,
                'comment': $('#comment-account-manager').val()
            }
            $.ajax({
                url: '/update/send-to-production',
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
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
                    alert("Возникла ошибка при обновлении данных !!!");
                }
            });
        }
    })


})