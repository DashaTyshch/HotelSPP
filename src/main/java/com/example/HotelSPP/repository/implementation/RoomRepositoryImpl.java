
package com.example.HotelSPP.repository.implementation;

import java.sql.*;

import com.example.HotelSPP.entity.Image;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.exceptions.ResourceNotFoundException;
import com.example.HotelSPP.repository.interfaces.RoomRepository;
//import com.sun.tools.javac.comp.Check;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;


@Slf4j
@Repository
@PropertySource("classpath:/config/roomType.properties")
public class RoomRepositoryImpl implements RoomRepository {

    @Value("${room_type.id}")
    private String paramRoomTypeId;
    @Value("${room_type.name_var}")
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

    @Value("${image.id}")
    private String paramImageId;
    @Value("${image.img}")
    private String paramImageImg;
    @Value("${image.room_id}")
    private String paramImageRoomId;

    @Value("${sql.insert.image}")
    private String ADD_IMAGE;
    @Value("${sql.select.imageByRoomId}")
    private String GET_IMAGE_BY_ROOM_ID;

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
        paramMap.put(paramRoomTypeDiscount, 0);
        namedTemplate.update(ADD_ROOM_TYPE, paramMap);

        Optional<RoomType> createdRoomType = findRoomTypeByName(roomType.getName());
        return createdRoomType.orElseThrow(
                () -> new ResourceNotFoundException("RoomType", "name", roomType.getName()));
    }

    @Override
    public void addImage(int id, String image) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(paramImageImg, image);
        paramMap.put(paramImageRoomId, id);
        namedTemplate.update(ADD_IMAGE, paramMap);
    }

    @Override
    public Optional<RoomType> findRoomTypeById(long id) {

        RoomType res;
        try {
            res = namedTemplate.queryForObject(GET_ROOM_TYPE_BY_ID, new MapSqlParameterSource(
                    paramRoomTypeId, id), new RoomTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room type by id: %s", id));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
//            throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for room type by !");
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<RoomType> findRoomTypeByName(String name) {
        RoomType res;
        try {
            res = namedTemplate.queryForObject(GET_ROOM_TYPE_BY_NAME, new MapSqlParameterSource(
                    paramRoomTypeName, name), new RoomTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find room type by name: %s", name));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<RoomType> findRoomTypesByPrice(float price) {
        RoomType res;
        try {
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
    public Optional<RoomType> updateRoomType(RoomType rt) {
        RoomType current;
        String updateSql = "UPDATE room_type SET ";
        Optional<RoomType> db_res;
        try {
            db_res = findRoomTypeById(rt.getId());
            if (db_res.isPresent()) {

                ArrayList<Object> params = new ArrayList<>();
                ArrayList<Integer> types = new ArrayList<>();
                current = db_res.get();
                if (!rt.getName().equals(current.getName())){
                    updateSql += " name= " + rt.getName();
                    params.add(rt.getName());
                    types.add(Types.VARCHAR);
                }
                if (!rt.getDescription().equals(current.getDescription())) {
                    updateSql += " description= " + rt.getDescription() + ", ";
                    params.add(rt.getDescription());
                    types.add(Types.VARCHAR);
                }
                if (rt.getAmount() != (current.getAmount())) {
                    updateSql += " amount= " + rt.getAmount() + ", ";
                    params.add(rt.getAmount());
                    types.add(Types.INTEGER);
                }
                if (rt.getPrice() != (current.getPrice())){
                    updateSql += " price= " + rt.getPrice() + ", ";
                    params.add(rt.getPrice());
                    types.add(Types.FLOAT);
                }
                if (rt.getPlaces() != (current.getPlaces())) {
                    updateSql += " places= " + rt.getPlaces() + ", ";
                    params.add(rt.getPlaces());
                    types.add(Types.INTEGER);
                }
                if (rt.getDiscount() != (current.getDiscount())) {
                    updateSql += " discount= " + rt.getDiscount() + ", ";
                    params.add(rt.getDiscount());
                    types.add(Types.INTEGER);
                }
                updateSql += " WHERE id =" + current.getId();

                int rows = namedTemplate.update(updateSql,(SqlParameterSource) params,(KeyHolder) types);
                System.out.println(rows + " row(s) updated.");
                db_res = findRoomTypeById(rt.getId());
                return db_res;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        try {
            return Collections.unmodifiableList(namedTemplate.query(GET_ALL_ROOM_TYPES, new RoomTypeMapper()));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    public List<Image> findImagesById(int id) {
        try {
            return Collections.unmodifiableList(namedTemplate.query(GET_IMAGE_BY_ROOM_ID,
                    new MapSqlParameterSource(paramImageRoomId, id), new ImageMapper()));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    private class RoomTypeMapper implements RowMapper<RoomType> {
        @Override
        public RoomType mapRow(ResultSet rs, int arg1) throws SQLException {
            return RoomType.builder()
                    .id(rs.getInt(paramRoomTypeId))
                    .name(rs.getString(paramRoomTypeName))
                    .description(rs.getString(paramRoomTypeDescription))
                    .amount(rs.getInt(paramRoomTypeAmount))
                    .price(rs.getFloat(paramRoomTypePrice))
                    .places(rs.getInt(paramRoomTypePlaces))
                    .discount(rs.getInt(paramRoomTypeDiscount))
                    .build();
        }
    }

    private class ImageMapper implements RowMapper<Image> {
        @Override
        public Image mapRow(ResultSet rs, int arg1) throws SQLException {
            return Image.builder()
                    .id(rs.getInt(paramImageId))
                    .image(rs.getString(paramImageImg))
                    .room_Type_Id(rs.getInt(paramImageRoomId))
                    .build();
        }
    }
}
