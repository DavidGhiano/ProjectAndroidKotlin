package com.neo.viewmodel

import androidx.lifecycle.ViewModel

class FibonacciViewModel: ViewModel() {

    fun SecuenciaFibonacci(cantidad:Int,numIngresado:Int=0):String{
        if (numIngresado!= 0){
            var contador = 1
            var isIgual:Int = Fibonacci(contador++)
            while (true){
                if(isIgual < numIngresado) isIgual = Fibonacci(contador++)
                else if (isIgual == numIngresado) {
                    return Secuencia(contador - 1, ((cantidad - 1)+(contador-1)))
                    break
                }
                else {
                    return (" El numero ingresado no es parte de la secuencia")
                    break
                }
            }
        }else{
            return Secuencia(0,cantidad-1)
        }
    }

    private fun Fibonacci(n:Int):Int{
        if(n>1) return Fibonacci(n-1)+Fibonacci(n-2)
        else if (n==1) return 1
        else if (n==0) return 0
        else return -1
    }

    private fun Secuencia(contador:Int, cantidad:Int):String{
        var secuencia:String =""
        for(i in contador..cantidad){
            secuencia = secuencia + " " + Fibonacci(i).toString()
        }
        return secuencia
    }
}