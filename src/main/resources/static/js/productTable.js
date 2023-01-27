$(document).ready(function () {

    let typeDate = '';
    let startDate = '';
    let endDate = '';
    let brend = '';
    let productName = '';
    let specification = ''
    let idSelected = ''


    const $searchFormProductTable = $('#container-product-table');

    var productTable = $('#productTable').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/api/v1/getProductTableData",
            "type": "POST",
            data: function (data) {
                typeDate = $('#select-type-date option:selected').val();
                startDate = $('#dateStart').val();
                let startTime = $('#timeStart').val();
                endDate = $('#dateEnd').val();
                var dateNow = new Date();
                var yearNow = dateNow.getFullYear();
                var monthNow = (dateNow.getMonth() + 1);
                if (monthNow < 10) {
                    monthNow = '0' + monthNow;
                }
                var dayNow = dateNow.getDate();
                if (dayNow < 10) {
                    dayNow = '0' + dayNow;
                }
                var hoursNow = dateNow.getHours();
                if (hoursNow < 10) {
                    hoursNow = '0' + hoursNow;
                }
                var minutesNow = dateNow.getMinutes();
                if (minutesNow < 10) {
                    minutesNow = '0' + minutesNow;
                }

                let endTime = $('#timeEnd').val();
                brend = $('#input1').val();
                productName = $('#input2').val();
                specification = $('#input3 option:selected').val();

                if (startDate === '') {
                    startDate = 'all';
                } else {
                    startDate = startDate + ' ' + startTime;
                }

                if (endDate === '') {
                    endDate = yearNow + '-' + monthNow + '-' + dayNow + ' ' + hoursNow + ':' + minutesNow;
                } else {
                    endDate = endDate + ' ' + endTime
                }
                orderColumn = data.order[0].column;
                orderType = data.order[0].dir;

                data.orderColumn = data.order[0].column;
                data.orderType = data.order[0].dir;
                data.typeDate = typeDate;
                data.startDate = startDate;
                data.endDate = endDate;
                data.brend = brend;
                data.productName = productName;
                data.specification = specification;
                data.requestFlag = 'request';
            }
        },
        "lengthMenu": [[15, 30, 60, 100, -1], [15, 30, 60, 100, "Все"]],
        "columns": [
            {"data": "idpr"},
            {"data": "datecr"},
            {"data": "datepl"},
            {"data": "datemade"},
            {"data": "brend"},
            {"data": "nameprod"},
            {"data": "sp"},
            {"data": "percent"},
            {"data": "mass"},
        ],
    });


    $('#productTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            let elem = $(this);
            idSelected = elem.find("td:first").html();
        } else {
            productTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelected = elem.find("td:first").html();
        }
    });

    $searchFormProductTable.submit(function (e) {
        e.preventDefault();
        productTable.ajax.reload();
    });


    $('#button8').click(function () {
        $('#loader').show();
        $.ajax({
            url: '/service/export/excel/productTable?orderColumn=' + orderColumn + '&orderType=' + orderType + '&typeDate=' + typeDate + '&startDate=' + startDate + '&endDate=' + endDate + '&brend=' + brend + '&productName=' + productName + '&specification=' + specification,
            data: $(this).serialize(),
            dataType: 'binary',
            xhrFields: {
                'responseType': 'blob'
            },
            success: function (data, status, xhr) {
                $('#loader').hide();
                var disposition = xhr.getResponseHeader('Content-Disposition');
                var link = document.createElement('a'),
                    filename = '';

                var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                var matches = filenameRegex.exec(disposition);
                if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');

                link.href = URL.createObjectURL(data);
                link.download = filename;
                link.click();
            }
        });
        return false;
    });


    $('#button4').on("click", function () {
        if (idSelected !== '') {
            let url = './dischargePage?id=' + idSelected;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    });

    $('#button3').on("click", function () {
        if (idSelected !== '') {
            let url = './recipePage?id=' + idSelected;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    });



    $('#button5').on("click", function () {
        if (idSelected !== '') {
            $.ajax({
                url: '/api/v1/printTestReport',
                method: 'POST',
                data: {id: idSelected},
                success: function (data) {
                    alert("Данные успешно отправленны на печать !");
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                    alert("Возникла ошибка при отправке данных на печать !!!");
                }
            });
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    });


});



