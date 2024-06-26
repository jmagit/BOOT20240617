package com.example.appliocation.contracts;

import java.sql.Timestamp;

import com.example.appliocation.models.NovedadesDTO;


public interface CatalogoService {

	NovedadesDTO novedades(Timestamp fecha);

}