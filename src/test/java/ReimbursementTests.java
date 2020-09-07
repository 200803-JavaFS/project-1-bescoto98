import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.ReimbursementDAO;
import com.revature.models.*;
import com.revature.services.*;

public class ReimbursementTests {
	public static ReimbursementService rs;
	public static ReimbursementDAO rdao;
	public static Reimbursement r;
	public static ReimbursementDTO rdto;
	public static double originalAmnt;
	public static Status originalStatus;
	public static R_Type originalType; 

	@BeforeClass
	public static void setVars() {
		rs = new ReimbursementService();
		rdto = new ReimbursementDTO();
		rdao = new ReimbursementDAO();
		

		r = rdao.findById(1);
		originalAmnt = r.getR_amnt();
		originalStatus = r.getR_status();
		originalType = r.getR_type();
	}
	
	@Before
	public void setValues() {
		r.setR_amnt(50.00);
		r.setR_status(rs.findStatusById(3));
		r.setR_type(rs.findType(4));
	}
	
	@Test
	public void testUpdate() {
		rs.updateReimbursement(r);
		rdto = rs.findById(1);
		assertTrue(rdto.r_amnt==50.00);
		assertTrue(rdto.r_status==3);
		assertTrue(rdto.r_type==4);
	}
	
	@AfterClass
	public static void undoChanges() {
		r.setR_amnt(originalAmnt);
		r.setR_status(originalStatus);
		r.setR_type(originalType);
		rs.updateReimbursement(r);
	}

}


