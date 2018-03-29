function getArticle(dataVal){
    $.ajax({
        url: "http://localhost:8080/tpv35/Controller/rechercheArticle.jsp",
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

toastr.options.closeMethod = 'fadeOut';
toastr.options.closeDuration = 300;
toastr.options.closeEasing = 'swing';

$(document).ready(function(){
    getArticle(null);
})

$('#sendLoginForm').submit(function(event) {
    event.preventDefault(); // Eviter que le formulaire se soumette
    var formData = $('#sendLoginForm').serialize();
    $.ajax({
        url: 'loginCode.jsp',
        type: 'post',
        data: formData
    })
        .done(function(data){
            if(data == "success") {
                window.location.href="http://localhost:8080/tpv35/mySpace.jsp?toastr=success";
            }
            else if (data == "mail_error")
                toastr.success("Champ mail invalide");
            else if(data == "mdp_error")
                toastr.success("Champ Mot de passe invalide");

            resetForm('#sendLoginForm'); // On vide le formulaire
        })
        .always(function()  {
            removeLoader("#loginSubmit");
        });
});

$('#sendRegisterForm').submit(function(event) {
    event.preventDefault(); // Eviter que le formulaire se soumette
    var formData = $('#sendRegisterForm').serialize();
    $.ajax({
        url: './Controller/registerCode.jsp',
        type: 'post',
        data: formData,
        cache: false,
    })
        .done(function(data){
            data = data.replace(/(?:\\[rn]|[\r\n]+)+/g, "");
            console.log(data);
            if(data == "success") {
                window.location.href="http://localhost:8080/tpv35/mySpace.jsp?toastr=success";
            }
            else if (data == "error_account_already")
                toastr.error("Un compte est déjà lié a ce mail");
            else if(data == "error_password_diff")
                toastr.error("Mots de passes différents");
            else if(data == "error_checkbox")
                toastr.error("Vous devez accepter les CGU");
            else if(data == "error_champs")
                toastr.error("Champs incomplet");

            resetForm('#sendRegisterForm'); // On vide le formulaire
        })
        .always(function()  {
            removeLoader("#registerSubmit");
        });
});

/*---------------------------------------------------------------------*/

// On met un loader sur l'élément qui a un attribut data-loader
$('form[data-loader]').submit(function(e) {
    if(!$(this).attr('data-loader-default')) {
        e.preventDefault();
    }
    var elem = $(this).attr('data-loader');
    setLoader(elem);
});
// Créer un loader sur l'élément
function setLoader(elem) {
    $(elem).prepend('<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>'); // On ajoute le loader
    $(elem).attr("disabled", "true"); // On rend le bouton non cliquable
}
// Supprimer le loader d'un élément
function removeLoader(elem) {
    $(elem).find('i').remove(); // On supprime le loader
    $(elem).attr("disabled", null); // On rend le bouton cliquable
}

// Vide tous les champs de texte d'un formulaire
function resetForm(idForm) {
    $(':input', idForm)
        .not(':button, :submit, :reset, :hidden, :checkbox')
        .val('')
        .removeAttr('selected');
}

//---------------------------------------------------------------------//