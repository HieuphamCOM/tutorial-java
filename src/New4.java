import inf.minife.fe.CircleS;
import inf.minife.fe.Constraint;
import inf.minife.fe.DOF;
import inf.minife.fe.Material;
import inf.minife.fe.Model;
import inf.minife.fe.Node;
import inf.minife.fe.Truss2D;
import inf.minife.fe.util.AnalysisTypes;
import inf.minife.view2.Viewer2;

public class New4 {
	public static void main(String[] args) {
		Model m = new Model();
		Node n1 = m.createNode(0.0, 0.0);
		Node n2 = m.createNode(0, 2.5);
		Node n3 = m.createNode(2.5, 2.5);
		Node n4 = m.createNode(5, 2.5);
		Node n5 = m.createNode(7.5, 2.5);
		Node n6 = m.createNode(9, 2.5);
		Node n7 = m.createNode(9, 0);

		Constraint c = new Constraint(false, false, true);
		Material mat = m.createMaterial(50000.0, 10.0);
		CircleS sct = m.createSection(CircleS.TYPE);

		n1.setConstraint(c);
		n7.setConstraint(c);
		sct.setDiameter(0.1);
		m.createElement(Truss2D.TYPE, mat, sct, n1, n2);
		m.createElement(Truss2D.TYPE, mat, sct, n2, n3);
		m.createElement(Truss2D.TYPE, mat, sct, n3, n4);
		m.createElement(Truss2D.TYPE, mat, sct, n4, n5);
		m.createElement(Truss2D.TYPE, mat, sct, n5, n6);
		m.createElement(Truss2D.TYPE, mat, sct, n6, n7);


		
		m.getSettings().setAcceleration(DOF.T_Y, 9.81);
		AnalysisTypes.nonlinearTransient(m,20,2000,1e-6);

		Viewer2 viewer = new Viewer2(m);
		viewer.setVisible(true);
	}
}
