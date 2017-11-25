var counter = 0;
app.controller("CanvasCtrl",function(Proyecto,$timeout,$state,$stateParams) {
    
    var self = this;


    var canvas, ctx, flag = false,
        prevX = 0,
        currX = 0,
        prevY = 0,
        currY = 0,
        dot_flag = false;

    var x = "black";
    self.y = 2;


    self.data = [
       

    ];

    self.proyecto = {"id":"","nombre":"","creador":{},"miembros":[],"dibujos":[]};

    self.grosor = [2,3,4,5,6];


    
    this.init=function() {
        canvas = document.getElementById('canvas');
        ctx = canvas.getContext("2d");
        w = canvas.width;
        h = canvas.height;
    
        canvas.addEventListener("mousemove", function (e) {
            findxy('move', e)
        }, false);
        canvas.addEventListener("mousedown", function (e) {
            findxy('down', e)
        }, false);
        canvas.addEventListener("mouseup", function (e) {
            findxy('up', e)
        }, false);
        canvas.addEventListener("mouseout", function (e) {
            findxy('out', e)
        }, false);
    }
    
    this.color=function(obj) {
        console.log(obj.target.id);
        switch (obj.target.id) {
            case "green":
                x = "green";
                break;
            case "blue":
                x = "blue";
                break;
            case "red":
                x = "red";
                break;
            case "yellow":
                x = "yellow";
                break;
            case "orange":
                x = "orange";
                break;
            case "black":
                x = "black";
                break;
            case "white":
                x = "white";
                break;
        }
        if (x == "white") y = 14;
        else this.y = 2;
    
    }
    
    this.draw =function(){
        ctx.beginPath();
        ctx.moveTo(prevX, prevY);
        ctx.lineTo(currX, currY);
        ctx.strokeStyle = x;
        ctx.lineWidth = this.y;
        ctx.stroke();
        ctx.closePath();
    }
    
    this.erase = function() {
        var m = confirm("Se borrara todo el lienzo");
        if (m) {
            ctx.clearRect(0, 0, w, h);
        }
    }
    
    this.save=function() {
        var dataURL = canvas.toDataURL("image/png");

    var arr = dataURL.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }

    let blob = new Blob([u8arr], {type:mime});
 

        this.proyecto.dibujos.push({"name":this.proyecto.dibujos.length +"","imagen":dataURL});
        console.log(this.proyecto.id );
       Proyecto.update({id: this.proyecto.id},this.proyecto, function() {
            self.notificarMensaje('Proyecto actualizado!');
            $state.reload();
        }, errorHandler);
       
    }
    
    function findxy(res, e) {
        var rect = canvas.getBoundingClientRect();

        if (res == 'down') {
            prevX = currX;
            prevY = currY;
            currX = e.clientX - rect.left;
            currY = e.clientY - rect.top;
    
            flag = true;
            dot_flag = true;
            if (dot_flag) {
                ctx.beginPath();
                ctx.fillStyle = x;
                ctx.fillRect(currX, currY, 2, 2);
                ctx.closePath();
                dot_flag = false;
            }
        }
        if (res == 'up' || res == "out") {
            flag = false;
        }
        if (res == 'move') {
            if (flag) {
                prevX = currX;
                prevY = currY;
                currX = e.clientX - rect.left;
                currY = e.clientY - rect.top;
                self.draw();
            }
        }
    }
    


    function errorHandler(error) {
        self.notificarError(error.data);
    }


    this.getProyecto = function(){
        Proyecto.query({id: $stateParams.proyectoId},function(data){
            self.proyecto = data;
            console.log(data);
        self.showImages();

        });

    }

    this.showImages = function(){
   
        $(".imagenes").innerHTML = "";
        for(var i=0;i<this.proyecto.dibujos.length;i++){
            $(".imagenes").append(" <div class='gallery_product filter hdpe' style='background-color:#ffffff; width:250px;height:250px;margin: 0px 10px 10px 0px;border: 3px solid #2455a5;display: inline-block;vertical-align:top;'><img class='img-responsive' src='" +self.proyecto.dibujos[i].imagen + "' width='200' height='200'></div>");
        }




    }


    this.getProyecto();




        this.msgs = [];
    this.notificarMensaje = function(mensaje) {
        this.msgs.push(mensaje);
        this.notificar(this.msgs);
    };

    this.errors = [];
    this.notificarError = function(mensaje) {
        this.errors.push(mensaje);
        this.notificar(this.errors);
    };

    this.notificar = function(mensajes) {
        $timeout(function() {
            while (mensajes.length > 0) mensajes.pop();
        }, 3000);
    };
    
});
