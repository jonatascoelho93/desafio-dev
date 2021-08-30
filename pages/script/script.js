var API_URL = 'http://localhost:9091/transacao/'

// Cria a tabela de trasações por loja detalhada
function detailFormatter(index, row) {
  var html = [];
  html.push(`<table class="table table-hover">
                  <thead>
                      <tr>
                          <th scope="col">Cod</th>
                          <th scope="col">Descrição</th>
                          <th scope="col">Data</th>
                          <th scope="col">Hora</th>
                          <th scope="col">CPF</th>
                          <th scope="col">Cartão</th>
                          <th scope="col">Valor</th>
                      </tr>
                  </thead>
                  <tbody>`)
  $.each(row['transacoes'], function (key, value) {
    html.push(`<tr><td>${value['tipoCodigo']}</td>`)
    html.push(`<td>${value['tipoDescricao']}</td>`)
    html.push(`<td>${value['data']}</td>`)
    html.push(`<td>${value['hora']}</td>`)
    html.push(`<td>${value['cpf'].replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4")}</td>`)
    html.push(`<td>${value['cartao']}</td>`)
    html.push(`<td align="right">${value['valor'].toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</td></tr>`)
  })
  html.push('</tbody></table>')

  return html.join('');
}

//Titulo da ultima linha da tabela
function total() {
  return 'Saldo Geral'
}

//Formata os valores de saldo pra moeda corrente
function priceFormatter(value) {
  return value.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
}

//Faz a soma do saldo geral
function totalPriceFormatter(data) {
  var field = this.field
  var total = 0;
  $.each(data, function (row, value) {
    total += value['saldo']
  })

  return total.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
}

//validar a extesão do arquivo selecionado
$("#input").change(function () {
  var nomeArquivo = this.files[0].name;
  var tamanhoNome = nomeArquivo.length;

  if (nomeArquivo.substr(tamanhoNome - 4, tamanhoNome) == ".txt") {
    $("#btnEnviar").removeAttr("disabled");
  } else {
    alert("Extensão do arquivo invalida!")
    $("#btnEnviar").attr("disabled", true);
  }
})


//requisição Ajax
$("#formulario").submit(function () {

  var formData = new FormData(this);

  $.ajax({
    url: API_URL + 'addAll',
    type: 'POST',
    data: formData,
    async: false,
    success: function () {
      alert("Dados atualizado com sucesso!");
      location.replace('http://localhost:9090');
      alert("");
    }, error: function () {
      alert("Erro em atualizar dados!");
      location.replace('http://localhost:9090');
      alert("");
    },

    cache: false,
    contentType: false,
    processData: false,
    xhr: function () {  // Custom XMLHttpRequest
      var myXhr = $.ajaxSettings.xhr();
      if (myXhr.upload) { // Avalia se tem suporte a propriedade upload
        myXhr.upload.addEventListener('progress', function () {
        }, false);
      }
      return myXhr;
    }
  });
});
