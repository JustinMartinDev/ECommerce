function getArticle(dataVal){
    $.ajax({
        url: "http://localhost:8080/tpv35/rechercheArticle.jsp",
        method: "POST",
        data : dataVal,
        cache: false
    })
    .done(function( html ) {
        console.log(html);
        $( "#contentArticle" ).html( html );
    })
    .fail(function(){
        alert("error");
    });
}

$(document).ready(function(){
    getArticle(null);
})