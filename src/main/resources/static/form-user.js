
//VALIDAÇÃO DOS CAMPOS

//nome
    let inputNome = document.getElementById('nome');

    inputNome.addEventListener("keydown", function(e) {  
        if (e.key > "0" && e.key <= "9") {
        e.preventDefault();
    }
    });

//CPF 
    let value_cpf = document.getElementById('cpf');

    value_cpf.addEventListener("keydown", function(e) {
        if (e.key > "a" && e.key < "z") {
        e.preventDefault();
    }
    });

    value_cpf.addEventListener("blur", function(e) {
        let validar_cpf = this.value.replace( /\D/g , "");
            if (validar_cpf.length == 11){
                var Soma;
                var Resto;
                //calculo da receita federal
                Soma = 0;
                for (i=1; i<=9; i++) Soma = Soma + parseInt(validar_cpf.substring(i-1, i)) * (11 - i);
                Resto = (Soma * 10) % 11;
            
                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(validar_cpf.substring(9, 10)) ) return alert("CPF Inválido!");
            
                Soma = 0;
                for (i = 1; i <= 10; i++) Soma = Soma + parseInt(validar_cpf.substring(i-1, i)) * (12 - i);
                Resto = (Soma * 10) % 11;
            
                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(validar_cpf.substring(10, 11) ) ) return alert("CPF Inválido!");
            
                cpf_final = validar_cpf.replace( /(\d{3})(\d)/ , "$1.$2");
                cpf_final = cpf_final.replace( /(\d{3})(\d)/ , "$1.$2");
                cpf_final = cpf_final.replace(/(\d{3})(\d{1,2})$/ , "$1-$2");
                document.getElementById('cpf').value = cpf_final;

            } else {
            alert("CPF Inválido! Necessário 11 digitos.");
        }
    }) 

 //Celular
     let campo_celular = document.getElementById('celular');

     campo_celular.addEventListener("blur", function(e) {
        let celular = this.value.replace( /\D/g , "");

        if (celular.length==11){
            celular = celular.replace(/^(\d{2})(\d)/g,"($1) $2"); 
            resultado_celular = celular.replace(/(\d)(\d{4})$/,"$1-$2");
            document.getElementById('celular').value = resultado_celular;
        } else {
            alert("Celular incorreto! Digite 11 números.");
        
        }
     })

     //busca
    let inputBusca = document.getElementById('input-search');

    inputBusca.addEventListener("keydown", function(e) {  
        if (e.key > "0" && e.key <= "9") {
        e.preventDefault();
    }
    });



 