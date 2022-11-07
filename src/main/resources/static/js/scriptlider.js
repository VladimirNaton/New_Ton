$(document).ready(function () {

    let orderColumn = '';
    let orderType = '';
    let startDate = '';
    let endDate = '';
    let scales = '';
    let fioOper = '';


    const $searchFormLiderTable = $('#container-lider-table-filter');


    var liderTable = $('#liderTable').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/api/v1/getTableData",
            "type": "POST",
            data: function (data) {

                startDate = $('#dateStart').val();
                let startTime = $('#timeStart').val();
                endDate = $('#dateEnd').val();
                let endTime = $('#timeEnd').val();
                scales = $('#scales option:selected').val();
                fioOper = $('#fioOper option:selected').val();

                if (startDate === '') {
                    startDate = 'all';
                } else {
                    startDate = startDate + ' ' + startTime;
                }

                if (endDate === '') {
                    endDate = 'all'
                } else {
                    endDate = endDate + ' ' + endTime
                }
                orderColumn = data.order[0].column;
                orderType = data.order[0].dir;

                data.orderColumn = data.order[0].column;
                data.orderType = data.order[0].dir;
                data.startDate = startDate;
                data.endDate = endDate;
                data.scales = scales;
                data.fioOper = fioOper;
                data.requestFlag = 'request';
            }
        },
        "lengthMenu": [[15,30,60,100,-1], [15,30,60,100, "Все"]],
        "columns": [
            {"data": "date"},
            {"data": "nw"},
            {"data": "plmass"},
            {"data": "factmass"},
            {"data": "operfio"}
        ],
    });


    $searchFormLiderTable.submit(function (e) {
        e.preventDefault();
        liderTable.ajax.reload();
    });

    $('#button4').click(function () {
        $('#loader').show();
        $.ajax({
            url: '/service/export/excel/calibrationTable?orderColumn=' + orderColumn + '&orderType=' + orderType + '&startDate=' + startDate + '&endDate=' + endDate + '&scales=' + scales + '&fioOper=' + fioOper,
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

    let operFio = function (){
        $.ajax({
            url: "/api/v1/getFioOper",
            method: "GET",
            success: function (data) {
                $.each(data, function (index, value) {
                    $('#fioOper').append('<option value="' + value + '">' + value + '</option>');
                });
            }
        });
    }
    operFio();

})
