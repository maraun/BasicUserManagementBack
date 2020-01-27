package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Position;

import java.util.List;

public interface PositionService {
    Position findById(Long id) throws ServiceException;
    List<Position> findAll();
    void deleteById(Long id) throws ServiceException;
    Position save(Position position) throws ServiceException;
    Position update(Position position) throws ServiceException;
}
