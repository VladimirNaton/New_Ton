$(document).ready(function () {


    let idMain = $('#idMain').text();


    $('#kind-prod').change(function () {
        let selectedComponents = $('#kind-prod').find('option:selected').val();

        switch (selectedComponents) {
            case '1':
                allRowHide();
                paintRowShow();
                allPrefillClear();
                prefillRowPaint();
                break;
            case '2':
                allRowHide();
                pastShow();
                allPrefillClear();
                prefillRowPast();
                break;
            case '3':
                allRowHide();
                solutionShow();
                allPrefillClear();
                prefillSolvent();
                break;
        }
    })

    function allRowHide() {
        $('#row2').hide();
        $('#row3').hide();
        $('#row4').hide();
        $('#row5').hide();
        $('#row6').hide();
        $('#row7').hide();
        $('#row8').hide();
        $('#row9').hide();
        $('#row10').hide();
        $('#row11').hide();
        $('#row12').hide();
        $('#row13').hide();
    }

    function paintRowShow() {
        $('#row2').show();
        $('#row5').show();
        $('#row6').show();
        $('#row7').show();
        $('#row8').show();
        $('#row9').show();
        $('#row10').show();
        $('#row11').show();
        $('#row13').show();
    }

    function solutionShow() {
        $('#row2').show();
        $('#row12').show();
        $('#row13').show();
    }

    function pastShow() {
        $('#row2').show();
        $('#row3').show();
        $('#row5').show();
        $('#row4').show();
        $('#row13').show();
    }

    function allPrefillClear() {
        $('#row2col2').val('');
        $('#row2col3').val('');
        $('#row2col4').val('');
        $('#row2col5').val('');
        $('#row3col2').val('');
        $('#row3col3').val('');
        $('#row3col4').val('');
        $('#row3col5').val('');
        $('#row4col2').val('');
        $('#row4col3').val('');
        $('#row4col4').val('');
        $('#row4col5').val('');
        $('#row5col2').val('');
        $('#row5col3').val('');
        $('#row5col4').val('');
        $('#row5col5').val('');
        $('#row6col2').val('');
        $('#row6col3').val('');
        $('#row6col4').val('');
        $('#row6col5').val('');
        $('#row7col2').val('');
        $('#row7col3').val('');
        $('#row7col4').val('');
        $('#row7col5-1').val('');
        $('#row7col5-2').val('');
        $('#row8col2').val('');
        $('#row8col3').val('');
        $('#row8col4').val('');
        $('#row8col5').val('');
        $('#row9col2').val('');
        $('#row9col3').val('');
        $('#row9col4').val('');
        $('#row9col5').val('');
        $('#row10col2').val('');
        $('#row10col3').val('');
        $('#row10col4').val('');
        $('#row10col5').val('');
        $('#row11col2').val('');
        $('#row11col3').val('');
        $('#row11col4').val('');
        $('#row11col5').val('');
        $('#row12col2').val('');
        $('#row12col3').val('');
        $('#row12col4').val('');
        $('#row12col5').val('');
        $('#row13col2').val('');
        $('#row13col3').val('');
        $('#row13col4').val('');
        $('#row13col5').val('');
    }

    function prefillRowPaint() {
        $('#row2col2').val('Візуально');
        $('#row2col3').val('Однорідне покриття, без мех.включень');
        $('#row2col4').val('');
        $('#row2col5').val('Відповідає');
        $('#row5col2').val('Внутріфірмова методика № 001');
        $('#row5col3').val('не менше 10 сек');
        $('#row5col4').val('± 2 с');
        $('#row5col5').val('');
        $('#row6col2').val('Внутріфірмова методика № 002');
        $('#row6col3').val('0,75-1,5 г/см3');
        $('#row6col4').val('± 0,01 гр.');
        $('#row6col5').val('');
        $('#row7col2').val('');
        $('#row7col3').val('не регламентується');
        $('#row7col4').val('± 2 гр.');
        $('#row7col5-1').val('');
        $('#row7col5-2').val('');
        $('#row8col2').val('');
        $('#row8col3').val('не регламентується');
        $('#row8col4').val('± 2 гр.');
        $('#row8col5').val('');
        $('#row9col2').val('');
        $('#row9col3').val('не регламентується');
        $('#row9col4').val('');
        $('#row9col5').val('');
        $('#row10col2').val('');
        $('#row10col3').val('не регламентується');
        $('#row10col4').val('');
        $('#row10col5').val('');
        $('#row11col2').val('Внутріфірмова методика № 007');
        $('#row11col3').val('згідно еталону');
        $('#row11col4').val('');
        $('#row11col5').val('Відповідає');
        $('#row13col2').val('Внутріфірмова методика № 006');
        $('#row13col3').val('згідно еталону');
        $('#row13col4').val('');
        $('#row13col5').val('Відповідає');
    }

    prefillRowPaint();

    function prefillRowPast() {
        $('#row2col2').val('Візуально');
        $('#row2col3').val('Однорідне покриття, без мех.включень');
        $('#row2col4').val('');
        $('#row2col5').val('Відповідає');
        $('#row3col2').val('Внутріфірмова методика № ');
        $('#row3col3').val('згідно еталону');
        $('#row3col4').val('');
        $('#row3col5').val('Відповідає');
        $('#row5col2').val('Внутріфірмова методика № ');
        $('#row5col3').val('T 20 - 22 °C 2100 - 2600 mPa.s');
        $('#row5col4').val('');
        $('#row5col5').val('');
        $('#row4col2').val('Внутріфірмова методика № 046');
        $('#row4col3').val('менше 7 мкм');
        $('#row4col4').val('± 2');
        $('#row4col5').val('');
        $('#row13col2').val('Внутріфірмова методика  № 006');
        $('#row13col3').val('згідно еталону');
        $('#row13col4').val('');
        $('#row13col5').val('Відповідає');
    }

    function prefillSolvent() {
        $('#row2col2').val('Візуально');
        $('#row2col3').val('Однорідне покриття, без мех.включень');
        $('#row2col4').val('');
        $('#row2col5').val('Відповідає');
        $('#row12col2').val('Внутріфірмова методика № 027');
        $('#row12col3').val('згідно еталону');
        $('#row12col4').val('');
        $('#row12col5').val('Відповідає');
        $('#row13col2').val('Внутріфірмова методика № 006');
        $('#row13col3').val('згідно еталону');
        $('#row13col4').val('');
        $('#row13col5').val('Відповідає');
    }

    function getDataForProtocol() {
        if (idMain !== '') {
            $.ajax({
                url: '/search/get-data-for-protocol/' + idMain,
                method: 'get',
                success: function (data) {
                    let yearNow = new Date().getFullYear();
                    let monthNow = new Date().getMonth() + 1;
                    let dayNow = new Date().getDate();
                    let hourNow = new Date().getHours();
                    let minutesNow = new Date().getMinutes();

                    let strDate = dateToString(yearNow, monthNow, dayNow, hourNow, minutesNow);

                    $('#date-create-protocol').val(strDate);
                    $('#date-create').val(strDate);
                    $('#date-footer').val(strDate);
                    $('#best-before-date').val(data.futureDate);
                    $('#brend').val(data.brend);
                    $('#name-prod').val(data.nameprod);
                    $('#name-prod-result').val(data.nameprod);
                    $('#lab-fio').val(data.labFio);
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

    getDataForProtocol();


    $('#save-protocol').click(function () {
        if (idMain !== '') {
            let data = createData();
            $.ajax({
                url: '/update/save-protocol',
                method: 'post',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data) {
                        alert("Данные успешно сохранены !!!");
                    } else {
                        alert("Возникла ошибка при сохранении данных !!!");
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                    alert("Возникла ошибка при сохранении данных !!!");
                }
            });

        }
    })

    function createData() {
        let dateProtocol = new Date($('#date-create-protocol').val());
        let strDateProtocol = dateToString(dateProtocol.getFullYear(), dateProtocol.getMonth(), dateProtocol.getDate(), dateProtocol.getHours(), dateProtocol.getMinutes());
        let numberProtocol = $('#number-protocol').val();
        let dateCreateProduct = new Date($('#date-create').val());
        let strDateCreateProduct =  dateToString(dateCreateProduct.getFullYear(), dateCreateProduct.getMonth(), dateCreateProduct.getDate(), dateCreateProduct.getHours(), dateCreateProduct.getMinutes());
        let partNumber = $('#part').val();
        let bestBeforeDate = new Date($('#best-before-date').val());
        let strBestBeforeDate = dateToString(bestBeforeDate.getFullYear(), bestBeforeDate.getMonth(), bestBeforeDate.getDate(), bestBeforeDate.getHours(), bestBeforeDate.getMinutes());
        let filter = $('#filter-select').find('option:selected').val();
        let tipProtocol = $('#kind-prod').find('option:selected').val();
        let row2col2 = $('#row2col2').val();
        let row2col3 = $('#row2col3').val();
        let row2col4 = $('#row2col4').val();
        let row2col5 = $('#row2col5').val();
        let row3col2 = $('#row3col2').val();
        let row3col3 = $('#row3col3').val();
        let row3col4 = $('#row3col4').val();
        let row3col5 = $('#row3col5').val();
        let row4col2 = $('#row4col2').val();
        let row4col3 = $('#row4col3').val();
        let row4col4 = $('#row4col4').val();
        let row4col5 = $('#row4col5').val();
        let row5col2 = $('#row5col2').val();
        let row5col3 = $('#row5col3').val();
        let row5col4 = $('#row5col4').val();
        let row5col5 = $('#row5col5').val();
        let row6col2 = $('#row6col2').val();
        let row6col3 = $('#row6col3').val();
        let row6col4 = $('#row6col4').val();
        let row6col5 = $('#row6col5').val();
        let row7col2 = $('#row7col2').val();
        let row7col3 = $('#row7col3').val();
        let row7col4 = $('#row7col4').val();
        let row7col5_1 = $('#row7col5-1').val();
        let row7col5_2 = $('#row7col5-2').val();
        let row8col2 = $('#row8col2').val();
        let row8col3 = $('#row8col3').val();
        let row8col4 = $('#row8col4').val();
        let row8col5 = $('#row8col5').val();
        let row9col2 = $('#row9col2').val();
        let row9col3 = $('#row9col3').val();
        let row9col4 = $('#row9col4').val();
        let row9col5 = $('#row9col5').val();
        let row10col2 = $('#row10col2').val();
        let row10col3 = $('#row10col3').val();
        let row10col4 = $('#row10col4').val();
        let row10col5 = $('#row10col5').val();
        let row11col2 = $('#row11col2').val();
        let row11col3 = $('#row11col3').val();
        let row11col4 = $('#row11col4').val();
        let row11col5 = $('#row11col5').val();
        let row12col2 = $('#row12col2').val();
        let row12col3 = $('#row12col3').val();
        let row12col4 = $('#row12col4').val();
        let row12col5 = $('#row12col5').val();
        let row13col2 = $('#row13col2').val();
        let row13col3 = $('#row13col3').val();
        let row13col4 = $('#row13col4').val();
        let row13col5 = $('#row13col5').val();


        let data = {
            'idMain': idMain,
            'dateProtocol': strDateProtocol,
            'numberProtocol': numberProtocol,
            'dateCreateProduct': strDateCreateProduct,
            'partNumber': partNumber,
            'bestBeforeDate': strBestBeforeDate,
            'filter': filter,
            'tipProtocol': tipProtocol,
            'row2col2': row2col2,
            'row2col3': row2col3,
            'row2col4': row2col4,
            'row2col5': row2col5,
            'row3col2': row3col2,
            'row3col3': row3col3,
            'row3col4': row3col4,
            'row3col5': row3col5,
            'row4col2': row4col2,
            'row4col3': row4col3,
            'row4col4': row4col4,
            'row4col5': row4col5,
            'row5col2': row5col2,
            'row5col3': row5col3,
            'row5col4': row5col4,
            'row5col5': row5col5,
            'row6col2': row6col2,
            'row6col3': row6col3,
            'row6col4': row6col4,
            'row6col5': row6col5,
            'row7col2': row7col2,
            'row7col3': row7col3,
            'row7col4': row7col4,
            'row7col5_1': row7col5_1,
            'row7col5_2': row7col5_2,
            'row8col2': row8col2,
            'row8col3': row8col3,
            'row8col4': row8col4,
            'row8col5': row8col5,
            'row9col2': row9col2,
            'row9col3': row9col3,
            'row9col4': row9col4,
            'row9col5': row9col5,
            'row10col2': row10col2,
            'row10col3': row10col3,
            'row10col4': row10col4,
            'row10col5': row10col5,
            'row11col2': row11col2,
            'row11col3': row11col3,
            'row11col4': row11col4,
            'row11col5': row11col5,
            'row12col2': row12col2,
            'row12col3': row12col3,
            'row12col4': row12col4,
            'row12col5': row12col5,
            'row13col2': row13col2,
            'row13col3': row13col3,
            'row13col4': row13col4,
            'row13col5': row13col5
        }
        return data;
    }

    function dateToString(year, month, day, hour, min) {

        if (month < 10) {
            month = '0' + month;
        }

        if (day < 10) {
            day = '0' + day;
        }

        if (hour < 10) {
            hour = '0' + hour;
        }

        if (min < 10) {
            min = '0' + min;
        }

        let date = year + '-' + month + '-' + day + ' ' + hour + ':' + min;
        return date;

    }


})