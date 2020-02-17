
package com.example.HotelSPP.repository.implementation;

import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.exceptions.ResourceNotFoundException;
import com.example.HotelSPP.repository.interfaces.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Slf4j
@Repository
@PropertySource("classpath:/config/roomType.properties")
public class RoomRepositoryImpl implements RoomRepository {

    @Value("${room_type.id}")
    private String paramRoomTypeId;
    @Value("${room_type.name}")
    private String paramRoomTypeName;
    @Value("${room_type.description}")
    private String paramRoomTypeDescription;
    @Value("${room_type.amount}")
    private String paramRoomTypeAmount;
    @Value("${room_type.price}")
    private String paramRoomTypePrice;
    @Value("${room_type.places}")
    private String paramRoomTypePlaces;
    @Value("${room_type.discount}")
    private String paramRoomTypeDiscount;

    @Value("${sql.insert.room_type}")
    private String ADD_ROOM_TYPE;

    @Value("${sql.select.room_typeById}")
    private String GET_ROOM_TYPE_BY_ID;

    @Value("${sql.select.room_typeByName}")
    private String GET_ROOM_TYPE_BY_NAME;

    @Value("${sql.select.room_typesByPrice}")
    private String GET_ROOM_TYPE_BY_PRICE;

    @Value("${sql.select.room_typesByPlaces}")
    private String GET_ROOM_TYPE_BY_PLACES;

    @Value("${sql.select.room_types}")
    private String GET_ALL_ROOM_TYPES;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;


    @Override
    public boolean roomTypeAvailable(String name) {
        return !findRoomTypeByName(name).isPresent();
    }

    @Override
    public RoomType addRoomType(RoomType roomType) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(paramRoomTypeName, roomType.getName());
        paramMap.put(paramRoomTypeDescription, roomType.getDescription());
        paramMap.put(paramRoomTypeAmount, roomType.getAmount());
        paramMap.put(paramRoomTypePrice, roomType.getPrice());
        paramMap.put(paramRoomTypePlaces, roomType.getPlaces());
        paramMap.put(paramRoomTypeDiscount, roomType.getDescription());
        try {
            namedTemplate.update(ADD_ROOM_TYPE, paramMap);
        } catch (DataAccessException e) {
            //throw RepositoryUtils.toDatabaseException(e, "Sorry, service can't create room type now !");
        }

        Optional<RoomType> createdRoomType = findRoomTypeByName(roomType.getName());
        return createdRoomType.orElseThrow(
                () -> new ResourceNotFoundException("RoomType", "name", roomType.getName()));
    }


    @Override
    public Optional<RoomType> findRoomTypeById(Long id) {

        RoomType res;
        try {
            log.debug("kek");
            res = namedTemplate.queryForObject(GET_ROOM_TYPE_BY_ID, new MapSqlParameterSource(
                    paramRoomTypeId, id), new RoomTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room type by id: %s", id));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for room type by !");
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<RoomType> findRoomTypeByName(String name) {
        RoomType res;
        try {
            log.debug("kek");
            res = namedTemplate.queryForObject(GET_ROOM_TYPE_BY_NAME, new MapSqlParameterSource(
                    paramRoomTypeName, name), new RoomTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room type by name: %s", name));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for room type by !");
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<RoomType> findRoomTypesByPrice(float price) {
        RoomType res;
        try {
            log.debug("kek");
            res = namedTemplate.queryForObject(GET_ROOM_TYPE_BY_PRICE, new MapSqlParameterSource(
                    paramRoomTypePrice, price), new RoomTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room type by price: %s", price));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for room type by !");
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<RoomType> findRoomTypesByPlaces(int places) {
        RoomType res;
        try {
            log.debug("kek");
            res = namedTemplate.queryForObject(GET_ROOM_TYPE_BY_PLACES, new MapSqlParameterSource(
                    paramRoomTypePlaces, places), new RoomTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room type by places: %s", places));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for room type by !");
        }
        return Optional.ofNullable(res);

    }

    @Override
    public Optional<List<RoomType>> findAllRoomTypes() {
        List<RoomType> res = new ArrayList<>();
        try {
            List<Map<String, Object>> rows =  jdbcTemplate.queryForList(GET_ALL_ROOM_TYPES);

            for (Map row : rows) {
                RoomType obj = new RoomType(0,((String) row.get("name")),
                        ((String) row.get("description")),
                        ((Integer) row.get("amount")),
                        ((Float) row.get("price")),
                        ((Integer) row.get("places")),
                        ((Integer) row.get("discount")));

                obj.setId((int) row.get("id"));
                res.add(obj);
            }
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room types: %s"));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for room type by !");
        }
        return Optional.ofNullable(res);

    }
    private class RoomTypeMapper implements RowMapper<RoomType> {
        @Override
        public RoomType mapRow(ResultSet rs, int arg1) throws SQLException {
            log.info("Room type variable: {}", paramRoomTypeName);
            return RoomType.builder()
                    .name(rs.getString(paramRoomTypeName))
                    .description(rs.getString(paramRoomTypeDescription))
                    .amount(rs.getInt(paramRoomTypeAmount))
                    .price(rs.getFloat(paramRoomTypePrice))
                    .places(rs.getInt(paramRoomTypePlaces))
                    .discount(rs.getInt(paramRoomTypeDiscount))
                    .build();
        }
    }
}
