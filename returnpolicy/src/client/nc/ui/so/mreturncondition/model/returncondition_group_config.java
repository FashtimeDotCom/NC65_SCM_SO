package nc.ui.so.mreturncondition.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class returncondition_group_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pubapp.pub.smart.SmartBatchAppModelService getBatchModelService(){
 if(context.get("batchModelService")!=null)
 return (nc.ui.pubapp.pub.smart.SmartBatchAppModelService)context.get("batchModelService");
  nc.ui.pubapp.pub.smart.SmartBatchAppModelService bean = new nc.ui.pubapp.pub.smart.SmartBatchAppModelService();
  context.put("batchModelService",bean);
  bean.setVoClass("nc.vo.so.mreturncondition.entity.ReturnConditionVO");
  bean.setServiceItf("nc.itf.so.mreturncondition.IReturnConditionMaintain");
return bean;
}

public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("boadatorfactory");
  nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
  context.put("boadatorfactory",bean);
return bean;
}

public nc.ui.so.mreturncondition.model.RetrunConditionValidationService getUiBatchValidateSerice(){
 if(context.get("uiBatchValidateSerice")!=null)
 return (nc.ui.so.mreturncondition.model.RetrunConditionValidationService)context.get("uiBatchValidateSerice");
  nc.ui.so.mreturncondition.model.RetrunConditionValidationService bean = new nc.ui.so.mreturncondition.model.RetrunConditionValidationService();
  context.put("uiBatchValidateSerice",bean);
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchBillTableModel getBatchBillTableModel(){
 if(context.get("batchBillTableModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchBillTableModel)context.get("batchBillTableModel");
  nc.ui.pubapp.uif2app.model.BatchBillTableModel bean = new nc.ui.pubapp.uif2app.model.BatchBillTableModel();
  context.put("batchBillTableModel",bean);
  bean.setContext(getContext());
  bean.setService(getBatchModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setValidationService(getUiBatchValidateSerice());
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.model.BatchModelDataManager bean = new nc.ui.pubapp.uif2app.model.BatchModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setService(getBatchModelService());
return bean;
}

public nc.ui.pubapp.uif2app.view.OrgPanel getOrgPanel(){
 if(context.get("orgPanel")!=null)
 return (nc.ui.pubapp.uif2app.view.OrgPanel)context.get("orgPanel");
  nc.ui.pubapp.uif2app.view.OrgPanel bean = new nc.ui.pubapp.uif2app.view.OrgPanel();
  context.put("orgPanel",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setDataManager(getModelDataManager());
  bean.setType("销售组织");/*-=notranslate=-*/
  bean.initUI();
return bean;
}

public nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter)context.get("componentValueManager");
  nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter bean = new nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter();
  context.put("componentValueManager",bean);
return bean;
}

public nc.ui.so.mreturncondition.view.ReturnConditionTable getList(){
 if(context.get("list")!=null)
 return (nc.ui.so.mreturncondition.view.ReturnConditionTable)context.get("list");
  nc.ui.so.mreturncondition.view.ReturnConditionTable bean = new nc.ui.so.mreturncondition.view.ReturnConditionTable();
  context.put("list",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setComponentValueManager(getComponentValueManager());
  bean.setMoreRowEdit(false);
  bean.setBodyMultiSelectEnable(true);
  bean.setIsBodyAutoAddLine(false);
  bean.setAddLineAction(getAddAction());
  bean.initUI();
return bean;
}

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.load();
return bean;
}

public nc.funcnode.ui.action.GroupAction getMaintainActionGroup(){
 if(context.get("maintainActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("maintainActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("maintainActionGroup",bean);
  bean.setCode("maintain");
  bean.setName("打印");/*-=notranslate=-*/
  bean.setActions(getManagedList0());
return bean;
}

public List getManagedList0(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  return list;}

public nc.ui.so.mreturncondition.action.AddLineAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.so.mreturncondition.action.AddLineAction)context.get("addAction");
  nc.ui.so.mreturncondition.action.AddLineAction bean = new nc.ui.so.mreturncondition.action.AddLineAction();
  context.put("addAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setVoClassName("nc.vo.so.mreturncondition.entity.ReturnConditionVO");
return bean;
}

public nc.ui.so.mreturncondition.action.EditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.mreturncondition.action.EditAction)context.get("editAction");
  nc.ui.so.mreturncondition.action.EditAction bean = new nc.ui.so.mreturncondition.action.EditAction();
  context.put("editAction",bean);
  bean.setModel(getBatchBillTableModel());
return bean;
}

public nc.ui.so.mreturncondition.action.ReturnConditionDelAction getDelAction(){
 if(context.get("delAction")!=null)
 return (nc.ui.so.mreturncondition.action.ReturnConditionDelAction)context.get("delAction");
  nc.ui.so.mreturncondition.action.ReturnConditionDelAction bean = new nc.ui.so.mreturncondition.action.ReturnConditionDelAction();
  context.put("delAction",bean);
  bean.setModel(getBatchBillTableModel());
return bean;
}

public nc.ui.uif2.actions.batch.BatchSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.uif2.actions.batch.BatchSaveAction)context.get("saveAction");
  nc.ui.uif2.actions.batch.BatchSaveAction bean = new nc.ui.uif2.actions.batch.BatchSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
  bean.setValidationService(getUiBatchValidateSerice());
return bean;
}

public nc.ui.so.mreturncondition.action.ReturnCondtionCanceAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.so.mreturncondition.action.ReturnCondtionCanceAction)context.get("cancelAction");
  nc.ui.so.mreturncondition.action.ReturnCondtionCanceAction bean = new nc.ui.so.mreturncondition.action.ReturnCondtionCanceAction();
  context.put("cancelAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.actions.SingleTablePrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.SingleTablePrintAction)context.get("printAction");
  nc.ui.pubapp.uif2app.actions.SingleTablePrintAction bean = new nc.ui.pubapp.uif2app.actions.SingleTablePrintAction();
  context.put("printAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setPreview(false);
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.actions.SingleTablePrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.SingleTablePrintAction)context.get("previewAction");
  nc.ui.pubapp.uif2app.actions.SingleTablePrintAction bean = new nc.ui.pubapp.uif2app.actions.SingleTablePrintAction();
  context.put("previewAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setPreview(true);
  bean.setEditor(getList());
return bean;
}

public nc.ui.so.mreturncondition.action.ReturnConditionRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.mreturncondition.action.ReturnConditionRefreshAction)context.get("refreshAction");
  nc.ui.so.mreturncondition.action.ReturnConditionRefreshAction bean = new nc.ui.so.mreturncondition.action.ReturnConditionRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setManager(getModelDataManager());
return bean;
}

public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
  bean.setNodeType(nc.vo.bd.pub.NODE_TYPE.GROUP_NODE);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getCNode_fa1add());
  bean.setActions(getManagedList1());
  bean.setEditActions(getManagedList2());
  bean.setModel(getBatchBillTableModel());
  bean.initUI();
return bean;
}

public nc.ui.uif2.tangramlayout.node.CNode getCNode_fa1add(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#fa1add")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#fa1add");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#fa1add",bean);
  bean.setComponent(getList());
return bean;
}

public List getManagedList1(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDelAction());  list.add(getSeparatorAction_1f9a9d2());  list.add(getRefreshAction());  list.add(getSeparatorAction_1fc5787());  list.add(getMaintainActionGroup());  return list;}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1f9a9d2(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1f9a9d2")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1f9a9d2");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1f9a9d2",bean);
return bean;
}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1fc5787(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1fc5787")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1fc5787");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1fc5787",bean);
return bean;
}

public List getManagedList2(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSeparatorAction_1d67493());  list.add(getCancelAction());  return list;}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1d67493(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1d67493")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1d67493");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1d67493",bean);
return bean;
}

}
