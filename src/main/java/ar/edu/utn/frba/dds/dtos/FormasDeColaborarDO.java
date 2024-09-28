package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FormasDeColaborarDO {
    String donarDinero;
    String donarViandas;
    String distribuirViandas;
    String administrarHeladeras;

}
