/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Expresion;

public class TipoVector {
    
    Expresion.TIPO_PRIMITIVO TIPO_PRIMITIVO;
    
    public TipoVector(Expresion.TIPO_PRIMITIVO TIPO_PRIMITIVO){
        this.TIPO_PRIMITIVO = TIPO_PRIMITIVO;
    }

    @Override
    public String toString() {
        return "TipoVector{" + "TIPO_PRIMITIVO=" + TIPO_PRIMITIVO + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoVector other = (TipoVector) obj;
        if (this.TIPO_PRIMITIVO != other.TIPO_PRIMITIVO) {
            return false;
        }
        return true;
    }
    
    
}
