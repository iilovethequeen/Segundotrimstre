#!/bin/bash
#ejercicios funciones
#funcion perimetro circulo
area_circulo() {
    local radio=$1
    local area=$(echo "scale=2; 3.14 * $radio * $radio" | bc)
    echo "El área del círculo es: $area"


}

#funcion volumen esfera
#funcion area rectangulo
#EN TODAS COMPROBAR NUMERO PARÁMETROS.