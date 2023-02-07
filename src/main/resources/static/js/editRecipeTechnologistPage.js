$(document).ready(function () {

    let selectedComponents = '1';
    let columnVisible = false;
    let findComponent = '';
    let codeSelectedElemRecipeTable;
    let idSelectedElemRecipeTable = '';
    let idSelectedElemComponentTable = '';
    let idMain = '';
    let nameComponent = '';
    let sequenceNumberFirstValue = '';
    let errorMessageShow = false;

    idMain = $('#id-edite-recipe').text();
    nameComponent = $("#component-select option:selected").text();


    let editeRecipeTable = $('#edite-recipe-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "lengthChange": false,
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
            {"data": "devmass"}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "orderable": false,
                "visible": false
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
            codeSelectedElemRecipeTable = '';
            idSelectedElemRecipeTable = '';
            sequenceNumberFirstValue = '';
            clearInputData();
            hideAllParameter();
        } else {
            editeRecipeTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            codeSelectedElemRecipeTable = elem[0].cells[2].innerText;
            showRecipeComponentInformation(codeSelectedElemRecipeTable);
            idSelectedElemRecipeTable = editeRecipeTable.row(this).data().id;
            getDataForSelectedRowEditeRecipeTable(idSelectedElemRecipeTable);
        }
    });

    function getDataForHeaderInformationString() {
        let idProd = $('#id-edite-recipe').text();
        if (idProd !== '') {
            $.ajax({
                url: '/search/get-data-for-head-string-edite-recipe/' + idProd,
                method: 'get',
                success: function (data) {
                    $('#edite-recipe-head-brend').val(data.brend);
                    $('#edite-recipe-head-date-create').val(data.dateString);
                    $('#edite-recipe-head-name-prod').val(data.nameProd);
                    $('#temp-min').val(data.tempMin);
                    $('#temp-max').val(data.tempMax);
                    $('#common-weight-edite-recipe').val(data.mass);
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

    let componentTable = $('#component-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-edite-recipe-component-table",
            "type": "POST",
            data: function (data) {
                data.codeSearch = selectedComponents;
                data.findComponent = findComponent;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "id"},
            {"data": "dateStr"},
            {"data": "nameraw"}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "orderable": false,
                "visible": false
            },
            {
                "targets": 1,
                "orderable": false,
                "visible": false
            }
            ,
            {
                "targets": 2,
                "orderable": false
            }
        ]
    });

    $('#component-select').change(function () {
        selectedComponents = $('#component-select').find('option:selected').val();
        nameComponent = $("#component-select option:selected").text();
        idSelectedElemComponentTable = '';
        switch (selectedComponents) {
            case '1':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '2':
                if (!columnVisible) {
                    showFirstColumn();
                    columnVisible = true;
                }
                $('#container-past-edite').show();
                break;
            case '3':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '4':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '5':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '6':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '7':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '8':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
        }
        $('#find-component-input').val('');
        findComponent = '';
        componentTable.ajax.reload();
    })


    $('#component-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            idSelectedElemComponentTable = '';
        } else {
            componentTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            idSelectedElemComponentTable = componentTable.row(this).data().id;
        }
    });

    function showFirstColumn() {
        componentTable.columns([1]).visible(true);
    }

    function hideFirstColumn() {
        componentTable.columns([1]).visible(false);
    }

    $('#find-component').click(function () {
        findComponent = $('#find-component-input').val();
        componentTable.ajax.reload();
    })

    $('#reset-find-component').click(function () {
        findComponent = '';
        $('#find-component-input').val('');
        componentTable.ajax.reload();
    })

    function showRecipeComponentInformation(code) {
        switch (code) {
            case '1':
                hideAllParameter();
                showCodeParameterComponent();
                break;
            case '2':
                hideAllParameter();
                showCodeParameterPast();
                break;
            case '3':
                hideAllParameter();
                break;
            case '4':
                hideAllParameter();
                showFilterParameter();
                break;
            case '5':
                hideAllParameter();
                break;
            case '6':
                hideAllParameter();
                showSolventParameter();
                break;
            case '7':
                hideAllParameter();
                break;
            case '8':
                hideAllParameter();
                showMixParameter();
                break;
        }
    }

    function showCodeParameterComponent() {
        $('#percent').show();
        $('#mass').show();
        $('#infelicity-percent').show();
        $('#infelicity-mass').show();
    }

    function showCodeParameterPast() {
        $('#percent').show();
        $('#mass').show();
        $('#infelicity-percent').show();
        $('#infelicity-mass').show();
        $('#part-label').show();
        $('#part-date-label').show();
    }

    function hideAllParameter() {
        $('#percent').hide();
        $('#mass').hide();
        $('#mixing').hide();
        $('#mixing-time').hide();
        $('#filter').hide();
        $('#infelicity-percent').hide();
        $('#infelicity-mass').hide();
        $('#part-label').hide();
        $('#part-date-label').hide();
    }

    function showFilterParameter() {
        $('#filter').show();
    }

    function showSolventParameter() {
        $('#mass').show();
    }

    function showMixParameter() {
        $('#mixing').show();
        $('#mixing-time').show();
    }

    $('#remove-component-from-recipe').click(function () {
        if (idSelectedElemRecipeTable !== '') {
            $.ajax({
                url: '/update/delete-selected-row-from-recipe-table/' + idSelectedElemRecipeTable,
                method: 'delete',
                success: function (data) {
                    if (data) {
                        editeRecipeTable.ajax.reload();
                        idSelectedElemComponentTable = '';
                        clearInputData();
                        hideAllParameter();
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

    function getDataForSelectedRowEditeRecipeTable(id) {
        $.ajax({
            url: '/search/get-data-for-selected-row-edite-recipe-table/' + id,
            method: 'get',
            success: function (data) {
                if (codeSelectedElemRecipeTable === '1') {
                    compliteComponentRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '2') {
                    complitePastRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '6') {
                    compliteSolventRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '8') {
                    compliteMixingRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '4') {
                    compliteFilterRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '3' || codeSelectedElemRecipeTable === '5' || codeSelectedElemRecipeTable === '7') {
                    compliteAnotherRowInformation(data);
                }

                checkMassAndInfelicityMass();
                sequenceNumberFirstValue = $('#sequence-number-input').val();
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
            }
        });
    }

    function compliteComponentRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#percent-input').val(data.percent);
        $('#mass-input').val(data.mass);
        $('#infelicity-percent-input').val(data.devper);
        $('#infelicity-mass-input').val(data.devmass);
    }

    function complitePastRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#percent-input').val(data.percent);
        $('#mass-input').val(data.mass);
        $('#infelicity-percent-input').val(data.devper);
        $('#infelicity-mass-input').val(data.devmass);
        $('#part').val(data.pastpart);
        $('#past-date').val(data.dateString);
    }

    function compliteSolventRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#mass-input').val(data.mass);
    }

    function compliteMixingRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#mixing-input').val(data.turnmix);
        $('#mixing-time-input').val(data.timemix);
    }

    function compliteFilterRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#filter-input').val(data.filter);
    }

    function compliteAnotherRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
    }

    $('#common-weight-edite-recipe').change(function () {
        let commonWeight = Math.round(Number($('#common-weight-edite-recipe').val()));
        if (commonWeight !== 0) {
            let percent = Number($('#percent-input').val());
            if (percent !== 0) {
                let result = (commonWeight / 100) * percent;
                $('#mass-input').val(result.toFixed(2));
            }

            let infelicityPercent = Number($('#infelicity-percent-input').val());
            if (infelicityPercent !== 0) {
                let result = (commonWeight / 100) * infelicityPercent;
                $('#infelicity-mass-input').val(result.toFixed(2))
            }
        }
        let tableData = getTableData();
        let mass = Math.round(Number(calculateMassElements(tableData)));

        if (mass !== commonWeight) {
            $('#control').css('background-color', 'crimson');
        } else {
            $('#control').css('background-color', 'white');
        }

    })

    $('#percent-input').change(function () {
        let percent = Number($('#percent-input').val());
        if (percent !== 0) {
            let result = calculateMass(percent);
            $('#mass-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#mass-input').change(function () {
        let mass = Number($('#mass-input').val());
        if (mass !== 0 && $("#percent").is(":visible")) {
            let result = calculatePercent(mass);
            $('#percent-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#infelicity-percent-input').change(function () {
        let percent = Number($('#infelicity-percent-input').val());
        if (percent !== 0) {
            let result = calculateInfelicityMass(percent);
            $('#infelicity-mass-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#infelicity-mass-input').change(function () {
        let mass = Number($('#infelicity-mass-input').val());
        if (mass !== 0) {
            let result = calculateInfelicityPercent(mass);
            $('#infelicity-percent-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#stage-input').change(function () {
        let stage = Number($('#stage-input').val());
        if (stage !== 0) {
            $('#error-message').hide();
        }
        sequenceNumberError();
    })


    function checkMassAndInfelicityMass() {
        let massInfelicity = Number($('#infelicity-mass-input').val()).toFixed(2);
        let percentInfelicity = Number($('#infelicity-percent-input').val()).toFixed(2);
        let percent = Number($('#percent-input').val()).toFixed(2);
        let mass = Number($('#mass-input').val()).toFixed(2);
        let calculatedMass = calculateMass(percent).toFixed(2);
        if (calculatedMass !== mass) {
            $('#mass-input').css('background-color', 'crimson');
        } else {
            $('#mass-input').css('background-color', 'white');
        }

        let calculatedInfelicityMass = calculateInfelicityMass(percentInfelicity).toFixed(2);
        if (calculatedInfelicityMass !== massInfelicity) {
            $('#infelicity-mass-input').css('background-color', 'crimson');
        } else {
            $('#infelicity-mass-input').css('background-color', 'white');
        }
    }

    function calculateMass(percent) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = (commonWeight / 100) * percent;
        return result;
    }

    function calculatePercent(mass) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = mass / (commonWeight / 100);
        return result;
    }

    function calculateInfelicityMass(percent) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = (commonWeight / 100) * percent;
        return result;
    }

    function calculateInfelicityPercent(mass) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = mass / (commonWeight / 100);
        return result;
    }


    editeRecipeTable.on('draw', function () {
        checkCommonPercents();
    });

    function getTableData() {
        let tableData = editeRecipeTable.rows().data();
        return tableData;
    }

    function calculatePercents(data) {
        let percents = 0;
        $.each(data, function (key, value) {
            percents += value.percent;
        });
        return percents;
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
        let percents = Number(calculatePercents(tableData)).toFixed(2);
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

    $('#add-component').click(function () {
        if (idSelectedElemComponentTable !== '' && idMain !== '') {
            addNewComponentToRecipe(idSelectedElemComponentTable, idMain, '', '')
            clearInputData();
            hideAllParameter();
        } else {
            if (selectedComponents > 2 && selectedComponents < 9) {
                addNewComponentToRecipe(idSelectedElemComponentTable, idMain, nameComponent, selectedComponents);
                clearInputData();
                hideAllParameter();
            } else {
                alert("Вы не выбрали ни одной записи !!!");
            }

        }
    })

    function addNewComponentToRecipe(idSelectedElemComponentTable, idMain, nameComponent, code) {
        let data = {
            "idComponentTable": idSelectedElemComponentTable,
            "idMain": idMain,
            "nameSelectedComponent": nameComponent,
            "code": code
        }
        $.ajax({
            url: '/update/add-component-to-recipe',
            method: 'post',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data) {
                    editeRecipeTable.ajax.reload();
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

    $('#replace-component').click(function () {
        if (idSelectedElemRecipeTable !== '' && idSelectedElemComponentTable !== '' || idSelectedElemRecipeTable !== '' && (selectedComponents > 2 && selectedComponents < 9)) {
            replaceAjax();
        } else {
            alert("Вы не выбрали ни одной записи или не выбран элемент в одной из таблиц !!!");
        }
    })

    function replaceAjax() {
        let data = {
            "idComponentTable": idSelectedElemComponentTable,
            "idMain": idMain,
            "nameSelectedComponent": nameComponent,
            "code": selectedComponents,
            "idSelectedElemRecipeTable": idSelectedElemRecipeTable
        }
        $.ajax({
            url: '/update/replace-selected-recipe-element',
            method: 'put',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data) {
                    editeRecipeTable.ajax.reload();
                    clearInputData();
                    hideAllParameter();
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

    $('#save-change').click(function () {
        if (idSelectedElemRecipeTable !== '') {
            let sequenceNumber = $('#sequence-number-input').val();
            let stage = $('#stage-input').val();
            let percent = $('#percent-input').val();
            let mass = $('#mass-input').val();
            let mixing = $('#mixing-input').val();
            let mixingTime = $('#mixing-time-input').val();
            let filter = $('#filter-input').val();
            let infelicityPercent = $('#infelicity-percent-input').val();
            let infelicityMass = $('#infelicity-mass-input').val();
            let part = $('#part').val();
            let pastDate = $('#past-date').val();

            if (stage === '0') {
                errorStageMessage();
            } else {
                $('#error-message').hide();
            }
            if (stage !== '0' && !errorMessageShow) {
                let data = {
                    "sequenceNumber": sequenceNumber,
                    "stage": stage,
                    "percent": percent,
                    "mass": mass,
                    "mixing": mixing,
                    "mixingTime": mixingTime,
                    "filter": filter,
                    "infelicityPercent": infelicityPercent,
                    "infelicityMass": infelicityMass,
                    "part": part,
                    "pastDate": pastDate,
                    "selectedComponentId": idSelectedElemRecipeTable
                }
                $.ajax({
                    url: '/update/selected-row-of-recipe',
                    method: 'put',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(data),
                    success: function (data) {
                        if (data) {
                            editeRecipeTable.ajax.reload();
                            alert("Данные успешно обновленны !!!");
                            idSelectedElemRecipeTable = '';
                            clearInputData();
                            hideAllParameter();
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
        }
    })

    function clearInputData() {
        $('#sequence-number-input').val('');
        $('#stage-input').val('');
        $('#percent-input').val('');
        $('#mass-input').val('');
        $('#mixing-input').val('');
        $('#mixing-time-input').val('');
        $('#filter-input').val('');
        $('#infelicity-percent-input').val('');
        $('#infelicity-mass-input').val('');
        $('#part').val('');
        $('#past-date').val('');
        $('#name-prod-input').val('');
    }

    $('#save-recipe').click(function () {
        let comment = $('#comment-technologist').val();
        let tempMin = $('#temp-min').val();
        let tempMax = $('#temp-max').val();
        let commonWeight = $('#common-weight-edite-recipe').val();
        let idMain = $('#id-edite-recipe').text();
        let control = $('#control').val();
        let data = {
            "comment": comment,
            "tempMin": tempMin,
            "tempMax": tempMax,
            "commonWeight": commonWeight,
            "idMain": idMain,
            "control": control
        }
        $.ajax({
            url: '/update/save-recipe',
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
            }
        });
    })

    function checkSequenceNumber(data, sequenceNumber) {
        let present = false;
        $.each(data, function (key, value) {
            if (Number(sequenceNumber) === Number(value.n)) {
                present = true;
            }
        });
        return present;

    }

    $('#sequence-number-input').change(function () {
        sequenceNumberError();
    });

    function sequenceNumberError() {
        let dataTable = getTableData();
        let sequenceNumber = $('#sequence-number-input').val();

        let present = checkSequenceNumber(dataTable, sequenceNumber);
        if (present) {
            errorSequenceNumberMessage();
            errorMessageShow = true;
        }
        if (!present || sequenceNumber === sequenceNumberFirstValue) {
            $('#error-message').hide();
            errorMessageShow = false;
        }
    }

    function errorSequenceNumberMessage() {
        $('#error-message').text("Такой номер по порядку уже присутствует !!!");
        $('#error-message').show();

    }

    function errorStageMessage() {
        $('#error-message').text("Этап не может быть равен 0  !!!");
        $('#error-message').show();
    }
})