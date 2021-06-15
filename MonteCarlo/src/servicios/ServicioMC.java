package servicios;

import org.osoa.sca.annotations.Service;

@Service
public interface ServicioMC {

    public double calcularPi(int seed, long points);

}