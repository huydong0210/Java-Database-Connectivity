package prepared_statement.common;

import java.sql.ResultSet;

public interface IMapper<T> {
    T mapRow(ResultSet resultSet);
}
