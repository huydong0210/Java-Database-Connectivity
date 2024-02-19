package prepared_statement.common;

import javax.swing.tree.RowMapper;
import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String sql, IMapper<T> mapper, Object ... parameters );
}
