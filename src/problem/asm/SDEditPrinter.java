package problem.asm;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SDEditPrinter implements IClassStructurePrinter {
	
	private String firstClass;
	private List<IMethodCallData> methodCalls;

	public SDEditPrinter(List<IMethodCallData> methodCalls, String firstClass){
		this.firstClass = firstClass;
		this.methodCalls = methodCalls;
		
	}

	@Override
	public void printToFile(String file) {
		try {
			StringBuilder sb = new StringBuilder();
			OutputStream out = new FilterOutputStream(new FileOutputStream(file));
			
			StringBuilder classesString = new StringBuilder();
			Set<String> classSet = new HashSet<>();
			String nm = StringParser.parseClassName(this.firstClass);
			classesString.append(nm + ":" + nm + "[a]\n");
			classSet.add(nm);
			
			for (IMethodCallData meth : this.methodCalls) {
				String name = StringParser.parseClassName(meth.getMethodClass());
				if(!classSet.contains(name)){
					classSet.add(name);
					classesString.append(name + ":" + name + "\n");
				}
				sb.append(meth.toString() + "\n");
			}
			classesString.append("\n");
			classesString.append(sb.toString());
			out.write(classesString.toString().getBytes());
			out.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
