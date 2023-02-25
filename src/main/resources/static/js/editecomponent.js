$(document).ready(function () {

    let selectedComponents = '';
    let findComponent = '';
    let idSelectedComponent = '';


    selectedComponents = $("#select-component option:selected").val();


    let componentTable = $('#component-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-edite-component-table",
            "type": "POST",
            data: function (data) {
                data.codeSearch = selectedComponents;
                data.findComponent = findComponent;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "id",},
            {"data": "code1c"},
            {"data": "nameraw"},
            {"data": "code"},
            {"data": "part"},
            {"data": "dateStr"},

        ],
        "columnDefs": [
            {
                "targets": 0, "width": "10%",
                "orderable": false
            },
            {
                "targets": 1, "width": "10%",
                "orderable": false,
                "visible": true
            }
            ,
            {
                "targets": 2, "width": "40%",
                "orderable": false
            }
            ,
            {
                "targets": 3, "width": "10%",
                "orderable": false
            }
            ,
            {
                "targets": 4, "width": "15%",
                "orderable": false,
                "visible": true
            }
            ,
            {
                "targets": 5, "width": "25%",
                "orderable": false,
                "visible": true
            }
        ]
    });

    $('#component-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            idSelectedComponent = '';
            cleanFieldsData();
        } else {
            componentTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelectedComponent = elem[0].cells[0].innerText;
            getDateSelectedRow();
        }
    });

    $('#select-component').change(function () {
        selectedComponents = $("#select-component option:selected").val();
        componentTable.ajax.reload();
        idSelectedComponent = '';
        $('#search-component').val('')
        cleanFieldsData();
    })

    $('#search-button').click(function () {
        findComponent = $('#search-component').val();
        if (findComponent !== '') {
            componentTable.ajax.reload();
            idSelectedComponent = '';
            cleanFieldsData();
        }
    })

    function cleanFieldsData() {
        $('#code1c').val('');
        $('#nameraw').val('');
        $('#code').val('');
        $('#part').val('');
        $('#date').val('');
    }

    $('#clean-filter-button').click(function () {
        $('#search-component').val('');
        findComponent = '';
        idSelectedComponent = '';
        cleanFieldsData();
        componentTable.ajax.reload();
    })

    componentTable.on('draw', function () {
        cleanFieldsData()
        findComponent = '';
        idSelectedComponent = '';
    });

    $('#add-button').click(function () {
        if (idSelectedComponent === '') {
            let code1c = $('#code1c').val();
            let nameraw = $('#nameraw').val();
            let code = $('#code').val();
            let part = $('#part').val();
            let date = new Date($('#date').val());
            let data = {
                "code1c": code1c,
                "nameraw": nameraw,
                "code": code,
                "part": part,
                "date": date
            }

            $.ajax({
                url: '/update/add-component',
                method: 'post',
                data: data,
                success: function (data) {
                    if (data) {
                        alert("Компонент успешно добавлен !!!")
                        componentTable.ajax.reload();
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
            alert('Для обновления компонента нажмите кнопку Обновить  !!!')
        }
    })

    $('#update-button').click(function () {
        if (idSelectedComponent !== '') {
            let code1c = $('#code1c').val();
            let nameraw = $('#nameraw').val();
            let code = $('#code').val();
            let part = $('#part').val();
            let date = new Date($('#date').val());
            let data = {
                "code1c": code1c,
                "nameraw": nameraw,
                "code": code,
                "part": part,
                "date": date,
                "id" : idSelectedComponent
            }
            $.ajax({
                url: '/update/update-component',
                method: 'put',
                data: data,
                success: function (data) {
                    if (data) {
                        alert("Компонент успешно обновлен !!!")
                        idSelectedComponent = '';
                        componentTable.ajax.reload();
                        cleanFieldsData();
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
            alert('Для добавления компонента нажмите кнопку Добавить  !!!')
        }
    })

    $('#delete-button').click(function () {
        if (idSelectedComponent !== '') {
            $.ajax({
                url: '/update/delete-component/' + idSelectedComponent,
                method: 'delete',
                success: function (data) {
                    if (data) {
                        alert("Компонент успешно удален !!!")
                        idSelectedComponent = '';
                        componentTable.ajax.reload();
                        cleanFieldsData();
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
            alert('Вы не выбрали ни одной строки  !!!')
        }
    })


    function getDateSelectedRow() {
        if (idSelectedComponent !== '') {
            $.ajax({
                url: '/search/data-selected-component/' + idSelectedComponent,
                method: 'get',
                success: function (data) {
                    if (data) {
                        $('#code1c').val(data.code1c);
                        $('#nameraw').val(data.nameraw);
                        $('#code').val(data.code);
                        $('#part').val(data.part);
                        $('#date').val(data.dateStr);
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

