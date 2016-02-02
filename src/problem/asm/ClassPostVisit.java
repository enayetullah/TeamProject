package problem.asm;

import java.io.IOException;

public class ClassPostVisit extends AbstractVisitMethod{

	
	public ClassPostVisit(StringBuffer buffer) {
		super(buffer);
	}

	@Override
	public void execute(ITraverser t) throws IOException {
		this.buffer.append("}\"\n]\n");
	}

}
