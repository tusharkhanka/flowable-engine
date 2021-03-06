/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.cmmn.engine.impl.cmd;

import org.flowable.cmmn.api.runtime.PlanItemInstanceState;
import org.flowable.cmmn.engine.impl.persistence.entity.PlanItemInstanceEntity;
import org.flowable.cmmn.engine.impl.util.CommandContextUtil;
import org.flowable.engine.common.api.FlowableIllegalArgumentException;
import org.flowable.engine.common.impl.interceptor.CommandContext;

/**
 * @author Joram Barrez
 */
public class EnablePlanItemInstanceCmd extends AbstractNeedsPlanItemInstanceCmd {

    public EnablePlanItemInstanceCmd(String planItemInstanceId) {
        super(planItemInstanceId);
    }
    
    @Override
    protected void internalExecute(CommandContext commandContext, PlanItemInstanceEntity planItemInstanceEntity) {
        if (!PlanItemInstanceState.AVAILABLE.equals(planItemInstanceEntity.getState())
                && !PlanItemInstanceState.DISABLED.equals(planItemInstanceEntity.getState())) {
            throw new FlowableIllegalArgumentException("Can only enable a plan item instance which is in state AVAILABLE or DISABLED");
        }
        CommandContextUtil.getAgenda(commandContext).planEnablePlanItemInstanceOperation(planItemInstanceEntity);
    }
    
}
