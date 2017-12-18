/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.model;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author julia
 */
@Entity
public class Product {

    
    @Id
    @GeneratedValue
    private int productID;

    private String naam;
    private String omschrijving;
    private BigDecimal prijs;
    private int voorraad;
    private String urlAfbeelding;
    

    public Product() {
    }

    public Product(String naam) {
        this.naam = naam;
    }

    public Product(String naam, BigDecimal prijs, int voorraad) {
        this.naam = naam;
        this.prijs = prijs;
        this.voorraad = voorraad;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    public String getUrlAfbeelding() {
		return urlAfbeelding;
	}

	public void setUrlAfbeelding(String urlAfbeelding) {
		this.urlAfbeelding = urlAfbeelding;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	@Override
    public String toString() {
        String productDisplay = "ProductID: " + this.getProductID() + " Product: " + this.getNaam() + " Prijs: " + this.getPrijs() + " Voorraad: " + this.getVoorraad();
        return productDisplay;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((naam == null) ? 0 : naam.hashCode());
        result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
        result = prime * result + productID;
        result = prime * result + voorraad;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (naam == null) {
            if (other.naam != null) {
                return false;
            }
        } else if (!naam.equals(other.naam)) {
            return false;
        }
        if (prijs == null) {
            if (other.prijs != null) {
                return false;
            }
        } else if (!prijs.equals(other.prijs)) {
            return false;
        }
        if (productID != other.productID) {
            return false;
        }
        if (voorraad != other.voorraad) {
            return false;
        }
        return true;
    }


}


