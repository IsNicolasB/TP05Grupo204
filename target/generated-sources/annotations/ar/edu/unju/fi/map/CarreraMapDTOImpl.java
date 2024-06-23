package ar.edu.unju.fi.map;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T00:19:14-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CarreraMapDTOImpl implements CarreraMapDTO {

    @Override
    public CarreraDTO convertirAcarreraDTO(Carrera carrera) {
        if ( carrera == null ) {
            return null;
        }

        CarreraDTO carreraDTO = new CarreraDTO();

        carreraDTO.setCodigo( carrera.getCodigo() );
        carreraDTO.setNombre( carrera.getNombre() );
        carreraDTO.setDuracion( carrera.getDuracion() );
        carreraDTO.setEstado( carrera.getEstado() );

        return carreraDTO;
    }

    @Override
    public Carrera convertirCarreraDTOACarrera(CarreraDTO carreradto) {
        if ( carreradto == null ) {
            return null;
        }

        Carrera carrera = new Carrera();

        carrera.setCodigo( carreradto.getCodigo() );
        carrera.setNombre( carreradto.getNombre() );
        carrera.setDuracion( carreradto.getDuracion() );
        carrera.setEstado( carreradto.getEstado() );

        return carrera;
    }

    @Override
    public List<CarreraDTO> convertirListaCarrerasAListaCarrerasDTO(List<Carrera> listaCarrera) {
        if ( listaCarrera == null ) {
            return null;
        }

        List<CarreraDTO> list = new ArrayList<CarreraDTO>( listaCarrera.size() );
        for ( Carrera carrera : listaCarrera ) {
            list.add( convertirAcarreraDTO( carrera ) );
        }

        return list;
    }

    @Override
    public List<Carrera> convertirListaCarrerasDTOAListaCarrerass(List<CarreraDTO> listaCarreraDTO) {
        if ( listaCarreraDTO == null ) {
            return null;
        }

        List<Carrera> list = new ArrayList<Carrera>( listaCarreraDTO.size() );
        for ( CarreraDTO carreraDTO : listaCarreraDTO ) {
            list.add( convertirCarreraDTOACarrera( carreraDTO ) );
        }

        return list;
    }
}
