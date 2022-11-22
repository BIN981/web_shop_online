/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modal.admin.Statistic;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ChauDM
 */
public class AdminDashboardDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public AdminDashboardDAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
        }
    }

    public List<Statistic> getMonthlyRevenue(String fromDate, String toDate) {
        StringBuilder query = new StringBuilder();
        query.append("select t.[date],  \n"
                + "	sum(t.revenue) [revenue]\n"
                + "from (\n"
                + "		select CONVERT(nvarchar, MONTH(o.order_at)) + '/' + CONVERT(nvarchar, YEAR(o.order_at)) [date],\n"
                + "			(o.total_price - 2) as revenue\n"
                + "		from [order] o\n"
                + "		where o.[status] = 'COMPLETED'\n");
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            query.append("and MONTH(o.order_at) between MONTH(?) and MONTH(?)\n");
        } else {
            if (StringUtils.isNotBlank(fromDate)) {
                query.append("and MONTH(o.order_at) >= MONTH(?)\n");
            } else if (StringUtils.isNotBlank(toDate)) {
                query.append("and MONTH(o.order_at) <= MONTH(?)\n");
            }
        }
        query.append(") as t\n"
                + "group by t.[date]");
        List<Statistic> results = new ArrayList<>();
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, toDate);
            } else {
                if (StringUtils.isNotBlank(fromDate)) {
                    ps.setString(filterIndex++, fromDate);
                } else if (StringUtils.isNotBlank(toDate)) {
                    ps.setString(filterIndex++, toDate);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Statistic(rs.getString("date"), rs.getString("revenue")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Statistic> getDailyRevenue(String fromDate, String toDate) {
        StringBuilder query = new StringBuilder();
        query.append("select t.[date],  t.[month], t.[day],\n"
                + "	sum(t.revenue) [revenue]\n"
                + "from (\n"
                + "		select DAY(o.order_at) as [day],\n"
                + "		MONTH(o.order_at) as [month],\n"
                + "		CONVERT(nvarchar, DAY(o.order_at)) + '/' + CONVERT(nvarchar, MONTH(o.order_at)) [date],\n"
                + "			\n"
                + "			(o.total_price - 2) as revenue\n"
                + "		from [order] o\n"
                + "		where o.[status] = 'COMPLETED'\n");
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            query.append("and MONTH(o.order_at) = MONTH(?)\n");
            query.append("and DAY(o.order_at) between DAY(?) and DAY(?)\n");
        } else {
            if (StringUtils.isNotBlank(fromDate)) {
                query.append("and DAY(o.order_at) >= DAY(?)\n");
                query.append("and MONTH(o.order_at) = MONTH(?)\n");
            } else if (StringUtils.isNotBlank(toDate)) {
                query.append("and DAY(o.order_at) <= DAY(?)\n");
                query.append("and MONTH(o.order_at) = MONTH(?)\n");
            }
        }
        query.append(") as t\n"
                + "group by t.[date],t.[month], t.[day]\n"
                + "order by t.[month], t.[day]");
        List<Statistic> results = new ArrayList<>();
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, toDate);
            } else {
                if (StringUtils.isNotBlank(fromDate)) {
                    ps.setString(filterIndex++, fromDate);
                    ps.setString(filterIndex++, fromDate);
                } else if (StringUtils.isNotBlank(toDate)) {
                    ps.setString(filterIndex++, toDate);
                    ps.setString(filterIndex++, toDate);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Statistic(rs.getString("date"), rs.getString("revenue")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Statistic> getMonthlyOrders(String fromDate, String toDate) {
        StringBuilder query = new StringBuilder();
        query.append("select t.[date], t.[month], t.[year],"
                + "         count(t.[date]) as quantity from (\n"
                + "		select YEAR(o.order_at) as [year],\n"
                + "                 MONTH(o.order_at) as [month],\n"
                + "                 CONVERT(nvarchar, MONTH(o.order_at)) + '/' + CONVERT(nvarchar, YEAR(o.order_at)) [date]\n"
                + "		from [order] o\n"
                + "		where [status] <> 'CANCELED'\n");
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            query.append("and YEAR(o.order_at) = YEAR(?)\n");
            query.append("and MONTH(o.order_at) between MONTH(?) and MONTH(?)\n");
        } else {
            if (StringUtils.isNotBlank(fromDate)) {
                query.append("and MONTH(o.order_at) >= MONTH(?)\n");
                query.append("and YEAR(o.order_at) = YEAR(?)\n");
            } else if (StringUtils.isNotBlank(toDate)) {
                query.append("and MONTH(o.order_at) <= MONTH(?)\n");
                query.append("and YEAR(o.order_at) = YEAR(?)\n");
            }
        }
        query.append(") as t\n"
                + "	group by t.[date], t.[month], t.[year]\n"
                + "     order by t.[month], t.[year]");
        List<Statistic> results = new ArrayList<>();
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, toDate);
            } else {
                if (StringUtils.isNotBlank(fromDate)) {
                    ps.setString(filterIndex++, fromDate);
                    ps.setString(filterIndex++, fromDate);
                } else if (StringUtils.isNotBlank(toDate)) {
                    ps.setString(filterIndex++, toDate);
                    ps.setString(filterIndex++, toDate);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Statistic(rs.getString("date"), rs.getString("quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Statistic> getDailyOrders(String fromDate, String toDate) {
        StringBuilder query = new StringBuilder();
        query.append("select t.[date], t.[month], t.[day],"
                + "         count(t.[date]) as quantity from (\n"
                + "		select DAY(o.order_at) as [day],\n"
                + "                 MONTH(o.order_at) as [month],\n"
                + "                 CONVERT(nvarchar, DAY(o.order_at)) + '/' + CONVERT(nvarchar, MONTH(o.order_at)) [date]\n"
                + "		from [order] o\n"
                + "		where [status] <> 'CANCELED'\n");
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            query.append("and MONTH(o.order_at) = MONTH(?)\n");
            query.append("and DAY(o.order_at) between DAY(?) and DAY(?)\n");
        } else {
            if (StringUtils.isNotBlank(fromDate)) {
                query.append("and DAY(o.order_at) >= DAY(?)\n");
                query.append("and MONTH(o.order_at) = MONTH(?)\n");
            } else if (StringUtils.isNotBlank(toDate)) {
                query.append("and DAY(o.order_at) <= DAY(?)\n");
                query.append("and MONTH(o.order_at) = MONTH(?)\n");
            }
        }
        query.append(") as t\n"
                + "	group by t.[date], t.[month], t.[day]\n"
                + "     order by t.[month], t.[day]");
        List<Statistic> results = new ArrayList<>();
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, toDate);
            } else {
                if (StringUtils.isNotBlank(fromDate)) {
                    ps.setString(filterIndex++, fromDate);
                    ps.setString(filterIndex++, fromDate);
                } else if (StringUtils.isNotBlank(toDate)) {
                    ps.setString(filterIndex++, toDate);
                    ps.setString(filterIndex++, toDate);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Statistic(rs.getString("date"), rs.getString("quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Statistic> getRevenueByCategory(String fromDate, String toDate) {
        StringBuilder query = new StringBuilder();
        query.append("select t.category_id, \n"
                + "	c.name [categoryName], \n"
                + "	SUM(t.quantity * t.price) as amount\n"
                + "from (\n"
                + "	select\n"
                + "		p.category_id,\n"
                + "		p.price,\n"
                + "		od.quantity\n"
                + "	from order_detail od\n"
                + "	left join [order] o on o.id = od.order_id\n"
                + "	left join product_size ps on ps.id = od.product_size_id\n"
                + "	left join product p on p.id = ps.product_id\n"
                + "\n"
                + "	where p.id is not null and o.[status] = 'COMPLETED'\n");
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            query.append("and o.order_at between ? and ?\n");
        } else {
            if (StringUtils.isNotBlank(fromDate)) {
                query.append("and o.order_at >= ?\n");
            } else if (StringUtils.isNotBlank(toDate)) {
                query.append("and o.order_at <= ?\n");
            }
        }
        query.append(") as t\n"
                + "left join category c on c.id = t.category_id\n"
                + "group by t.category_id, c.name");
        List<Statistic> results = new ArrayList<>();
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, toDate);
            } else {
                if (StringUtils.isNotBlank(fromDate)) {
                    ps.setString(filterIndex++, fromDate);
                } else if (StringUtils.isNotBlank(toDate)) {
                    ps.setString(filterIndex++, toDate);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Statistic(rs.getString("categoryName"), rs.getString("amount")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    
    public List<Statistic> getQuantityByCategory(String fromDate, String toDate) {
        StringBuilder query = new StringBuilder();
        query.append("select t.category_id, \n"
                + "	c.name [categoryName], \n"
                + "	COUNT(t.quantity * t.price) as quantity\n"
                + "from (\n"
                + "	select\n"
                + "		p.category_id,\n"
                + "		p.price,\n"
                + "		od.quantity\n"
                + "	from order_detail od\n"
                + "	left join [order] o on o.id = od.order_id\n"
                + "	left join product_size ps on ps.id = od.product_size_id\n"
                + "	left join product p on p.id = ps.product_id\n"
                + "\n"
                + "	where p.id is not null and o.[status] = 'COMPLETED'\n");
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            query.append("and o.order_at between ? and ?\n");
        } else {
            if (StringUtils.isNotBlank(fromDate)) {
                query.append("and o.order_at >= ?\n");
            } else if (StringUtils.isNotBlank(toDate)) {
                query.append("and o.order_at <= ?\n");
            }
        }
        query.append(") as t\n"
                + "left join category c on c.id = t.category_id\n"
                + "group by t.category_id, c.name");
        List<Statistic> results = new ArrayList<>();
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
                ps.setString(filterIndex++, fromDate);
                ps.setString(filterIndex++, toDate);
            } else {
                if (StringUtils.isNotBlank(fromDate)) {
                    ps.setString(filterIndex++, fromDate);
                } else if (StringUtils.isNotBlank(toDate)) {
                    ps.setString(filterIndex++, toDate);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Statistic(rs.getString("categoryName"), rs.getString("quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void main(String[] args) {
        AdminDashboardDAO dao = new AdminDashboardDAO();
        System.out.println(dao.getRevenueByCategory(null, null));
    }
}
